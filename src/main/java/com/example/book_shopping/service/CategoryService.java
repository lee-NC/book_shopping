package com.example.book_shopping.service;

import com.example.book_shopping.entity.Category;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.DuplicateRecordException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.CategoryRepository;
import com.example.book_shopping.request.CreateCategoryRequest;
import com.example.book_shopping.request.UpdateCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lengo
 * created on 3/19/2022
 */
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    private boolean existById(int id) {
        try {
            return categoryRepository.existsById(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<Category> getAll() {
        try {
            List<Category> categories = categoryRepository.findAll();
            if (!categories.isEmpty()) return categories;
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Category get(int id) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if (category.isPresent()) return category.get();
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Category add(CreateCategoryRequest request) {
        try {
            if (categoryRepository.existsByName(request.getName().trim()))
                throw new DuplicateRecordException("Name was used");
            Category category = new Category();
            if (request.getDesc() != null) {
                category.setDescription(request.getDesc().trim());
            }
            category.setName(request.getName().trim());
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Category update(int id, UpdateCategoryRequest request) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if (category.isPresent()) {
                if (request.getDesc() != null && !category.get().getDescription().equals(request.getDesc().trim())) {
                    category.get().setDescription(request.getDesc().trim());
                    return categoryRepository.save(category.get());
                }
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean delete(int id) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if (category.isPresent()) {
                categoryRepository.delete(category.get());
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
