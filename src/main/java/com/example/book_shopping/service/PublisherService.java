package com.example.book_shopping.service;

import com.example.book_shopping.entity.Publisher;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.DuplicateRecordException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.PublisherRepository;
import com.example.book_shopping.request.CreatePublisherRequest;
import com.example.book_shopping.request.UpdatePublisherRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author lengo
 * created on 3/19/2022
 */
@Service
@Transactional
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAll() {
        try {
            return publisherRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Publisher get(int id) {
        try {
            Optional<Publisher> procedure = publisherRepository.findById(id);
            if (procedure.isPresent()) return procedure.get();
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Publisher add(CreatePublisherRequest request) {
        try {
            if (publisherRepository.existsByName(request.getName().trim())) {
                throw new DuplicateRecordException("Name was used");
            }
            Publisher publisher = new Publisher();
            publisher.setCountry(request.getCountry().trim());
            publisher.setName(request.getName().trim());
            publisher = publisherRepository.save(publisher);
            return publisher;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Publisher update(int id, UpdatePublisherRequest request) {
        try {
            Optional<Publisher> procedure = publisherRepository.findById(id);
            if (procedure.isPresent()) {
                if (request.getCountry() != null && !procedure.get().getCountry().equals(request.getCountry().trim())) {
                    if (publisherRepository.existsByName(request.getName().trim()))
                        throw new DuplicateRecordException("Name was used");
                    procedure.get().setCountry(request.getCountry().trim());

                }
                if (request.getName() != null && !procedure.get().getName().equals(request.getName().trim())) {
                    procedure.get().setCountry(request.getCountry().trim());

                }
                return publisherRepository.save(procedure.get());
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean delete(int id) {
        try {
            Optional<Publisher> procedure = publisherRepository.findById(id);
            if (procedure.isPresent()) {
                publisherRepository.delete(procedure.get());
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }
}
