package com.example.book_shopping.service;

import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.NotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author lengo
 * created on 3/9/2022
 */
@Service
public class FileStorageService {

    private static final String DOWNLOAD_DIR = "../book_shopping/src/main/resources/templates/image/";

    public String storeFile(MultipartFile file, String name) {
        try {
            Path fileStorageLocation = Paths.get(DOWNLOAD_DIR)
                    .toAbsolutePath().normalize();
            Files.createDirectories(fileStorageLocation);
            //cut type filename
            String[] typeFiles = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())).split(Pattern.quote("."));
            String typeFile = typeFiles[typeFiles.length - 1];
            // create filename by uuid
            UUID uuid = UUID.randomUUID();
            String fileName = name + uuid + "." + typeFile;
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path fileStorageLocation = Paths.get(DOWNLOAD_DIR)
                    .toAbsolutePath().normalize();
            Files.createDirectories(fileStorageLocation);
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new NotFoundException("File not found " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException(e.getMessage());
        }
    }

    /**
     * Resizes an image to an absolute width and height (the image may not be proportional)
     *
     * @param inputImagePath Path of the original image
     * @param scaledWidth    absolute width in pixels
     * @param scaledHeight   absolute height in pixels
     */
    private void resize(String inputImagePath, int scaledWidth, int scaledHeight) {
        try {
            // reads input image
            File inputFile = new File(inputImagePath);
            BufferedImage inputImage = ImageIO.read(inputFile);

            // creates output image
            BufferedImage outputImage = new BufferedImage(scaledWidth,
                    scaledHeight, inputImage.getType());

            // scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();

            // extracts extension of output file
            String formatName = inputImagePath.substring(inputImagePath
                    .lastIndexOf(".") + 1);

            // writes to output file
            ImageIO.write(outputImage, formatName, new File(inputImagePath));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    /**
     * Test resizing images
     */
    public void resizeImage(String inputImagePath) {

        try {
            // resize to a fixed width (not proportional)
            int scaledWidth = 261;
            int scaledHeight = 261;
            resize(inputImagePath, scaledWidth, scaledHeight);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }
}
