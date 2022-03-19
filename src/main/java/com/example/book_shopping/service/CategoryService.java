package com.example.book_shopping.service;

import com.example.book_shopping.entity.Category;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.DuplicateRecordException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.CategoryRepository;
import com.example.book_shopping.request.CreateCategoryRequest;
import com.example.book_shopping.request.StringRequest;
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
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Category get(int id) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if (category.isPresent()) return category.get();
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Category add(CreateCategoryRequest request) {
        try {
            if (categoryRepository.existsByName(request.getName().trim())) {
                throw new DuplicateRecordException("Name was used");
            }
            Category category = new Category();
            if (request.getDesc() != null) {
                category.setDescription(request.getDesc().trim());
            }
            category.setName(request.getName().trim());
            category = categoryRepository.save(category);
            return category;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Category update(int id, StringRequest request) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if (category.isPresent() && request.getText() != null && !category.get().getDescription().equals(request.getText().trim())) {
                category.get().setDescription(request.getText().trim());
                return categoryRepository.save(category.get());
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }
}
