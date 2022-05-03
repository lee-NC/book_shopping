package com.example.book_shopping.service;

import com.example.book_shopping.entity.*;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.*;
import com.example.book_shopping.request.CreateProductRequest;
import com.example.book_shopping.request.UpdateProductRequest;
import com.example.book_shopping.response.ProductResponse;
import com.example.book_shopping.response.UploadImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author lengo
 * created on 3/19/2022
 */
@Service
public class ProductService {
    private static final String UPLOAD_DIR = "../book_shopping/src/main/resources/templates/image/";
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private FileStorageService fileStorageService;

    public List<ProductResponse> getAllByCategoryId(int categoryId) {
        try {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isPresent()) {
                List<Product> products = productRepository.findAllByCategory(category.get());
                if (products != null) {
                    return toProductResponses(products);
                }
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<ProductResponse> getAllByPublisherId(int publisherId) {
        try {
            Optional<Publisher> procedure = publisherRepository.findById(publisherId);
            if (procedure.isPresent()) {
                List<Product> products = productRepository.findAllByPublisher(procedure.get());
                if (products != null) return toProductResponses(products);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<ProductResponse> getAllByLanguageId(int languageId) {
        try {
            Optional<Language> language = languageRepository.findById(languageId);
            if (language.isPresent()) {
                List<Product> products = productRepository.findAllByLanguage(language.get());
                if (products != null) return toProductResponses(products);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<ProductResponse> getAllByPrice(double greater, double less) {
        try {
            List<Product> products = productRepository.findAllByPriceGreaterThanAndPriceLessThan(greater, less);
            if (products == null) throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
            return toProductResponses(products);

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<ProductResponse> search(String keyword) {
        try {
            Set<Product> products = new HashSet<>();
            products.addAll(productRepository.searchProductRelative(keyword));
            products.addAll(productRepository.searchProductMatch(keyword));
            if (!products.isEmpty()) return toProductResponses(List.copyOf(products));
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<ProductResponse> getBestSale() {
        try {
            List<Product> products = orderProductRepository.findProductBestSales();
            if(products!=null){
                int size = products.size();
                if (size-1<=4 && size>0){
                    products = products.subList(0, size-1);
                }
                else products = products.subList(0, 4);
                return toProductResponses(products);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<ProductResponse> getAll() {
        try {
            List<Product> products = productRepository.findAll();
            return toProductResponses(products);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public ProductResponse get(int id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                return toProductResponse(product.get());
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public ProductResponse add(CreateProductRequest request) {
        try {
            Optional<Category> category = categoryRepository.findById(request.getCategoryId());
            Optional<Publisher> procedure = publisherRepository.findById(request.getPublisherId());
            Optional<Language> language = languageRepository.findById(request.getLanguageId());
            if (procedure.isPresent() && category.isPresent() && language.isPresent()) {
                Product product = new Product();
                product.setAmount(request.getAmount());
                product.setCategory(category.get());
                product.setPrice(request.getPrice());
                product.setName(request.getName().trim());
                product.setPublisher(procedure.get());
                product.setPublishingYear(request.getPublishingYear());
                product.setDescription(request.getDescription().trim());
                product.setLanguage(language.get());
                product = productRepository.save(product);
                return toProductResponse(product);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public ProductResponse addImage(int id, String name) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                ProductImage productImage = new ProductImage();
                name = name.toLowerCase(Locale.ROOT);
                productImage.setName(name);
                productImage = productImageRepository.save(productImage);
                Product data = product.get();
                Set<ProductImage> productImages = data.getProductImages();
                productImages.add(productImage);
                data.setProductImages(productImages);
                data = productRepository.save(data);
                return toProductResponse(data);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<UploadImageResponse> uploadMultipleImages(MultipartFile[] files, int id) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadImage(file, id))
                .collect(Collectors.toList());
    }

    private UploadImageResponse uploadImage(MultipartFile file, int id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent() && file != null && file.getSize() >= 2000 && file.getSize() <= 5242880) {
                Product data = product.get();
                String fileType = Objects.requireNonNull(file.getContentType()).split(Pattern.quote("/"))[0];
                if (fileType.equals("image")) {
                    String fileName = fileStorageService.storeFile(file, product.get().getName());
                    fileStorageService.resizeImage(UPLOAD_DIR + fileName);
                    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/image/download/")
                            .path(fileName)
                            .toUriString();
                    ProductImage productImage = new ProductImage();
                    productImage.setName(fileName);
                    productImage.setProduct(data);
                    productImage = productImageRepository.save(productImage);
                    Set<ProductImage> productImages = data.getProductImages();
                    productImages.add(productImage);
                    UploadImageResponse uploadImageResponse = new UploadImageResponse(fileName, fileDownloadUri,
                            file.getContentType(), product.get().getId());
                    return uploadImageResponse;
                }
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public ResponseEntity<Resource> downloadImage(String fileName, HttpServletRequest request) {
        // Load file as Resource
        try {
            Resource resource = fileStorageService.loadFileAsResource(fileName);
            // Try to determine file's content type
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            // Fallback to the default content type if type could not be determined
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public ProductResponse update(int id, UpdateProductRequest request) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                Product data = product.get();
                if (request.getCategoryId() != 0) {
                    Optional<Category> category = categoryRepository.findById(request.getCategoryId());
                    if (category.isPresent() && !data.getCategory().equals(category.get())) {
                        data.setCategory(category.get());
                    }
                }
                if (request.getPublisherId() != 0) {
                    Optional<Publisher> procedure = publisherRepository.findById(request.getPublisherId());
                    if (procedure.isPresent() && !data.getPublisher().equals(procedure.get())) {
                        data.setPublisher(procedure.get());
                    }
                }
                if (request.getLanguageId() != 0) {
                    Optional<Language> language = languageRepository.findById(request.getLanguageId());
                    if (language.isPresent() && !data.getLanguage().equals(language.get())) {
                        data.setLanguage(language.get());
                    }
                }
                if (request.getAmount() != 0 && request.getAmount() != data.getAmount()) {
                    data.setAmount(request.getAmount());
                    if (product.get().getAmount() == 0) data.setActive(false);
                }
                if (request.getPrice() != 0 && request.getPrice() != data.getPrice()) {
                    data.setPrice(request.getPrice());
                }
                if (request.getDescription() != null && !request.getDescription().equals(data.getDescription())) {
                    data.setDescription(request.getDescription());
                }
                if (request.getName() != null && !request.getName().equals(data.getName())) {
                    data.setName(request.getName());
                }
                if (request.getPublishingYear() != 0 && request.getPublishingYear() != data.getPublishingYear()) {
                    data.setPublishingYear(request.getPublishingYear());
                }
                return toProductResponse(productRepository.save(data));
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public ProductResponse changeStatus(int id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                product.get().setActive(!product.get().isActive());
                if (product.get().isActive() && product.get().getAmount() == 0)
                    throw new BadRequestException("Product is sold out");
                return toProductResponse(productRepository.save(product.get()));
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean delete(int id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                productRepository.delete(product.get());
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean deleteImage(String name) {
        try {
            name = name.toLowerCase(Locale.ROOT);
            ProductImage productImage = productImageRepository.findByName(name);
            if (productImage != null) {
                productImageRepository.delete(productImage);
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    private List<ProductResponse> toProductResponses(List<Product> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(toProductResponse(product));
        }
        return productResponses;
    }

    private ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setDesc(product.getDescription());
        response.setName(product.getName());
        response.setActive(product.isActive());
        response.setAmount(product.getAmount());
        Locale locale = new Locale("vi", "VN");
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        response.setPrice(decimalFormat.format(product.getPrice()) + " đ");//revert to VND
        response.setLanguage(product.getLanguage());
        response.setPublishingYear(product.getPublishingYear());
        response.setCategory(product.getCategory());
        response.setPublisher(product.getPublisher());
        Set<ProductImage> productImages = new HashSet<>();
        if (product.getProductImages() != null) productImages = product.getProductImages();
        List<String> imageNames = new ArrayList<>();
        for (ProductImage productImage : productImages) {
            imageNames.add(productImage.getName());
        }
        response.setImageName(imageNames);
        return response;
    }


}
