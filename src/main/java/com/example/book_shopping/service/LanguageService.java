package com.example.book_shopping.service;

import com.example.book_shopping.entity.Language;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.DuplicateRecordException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.LanguageRepository;
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
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getAll() {
        try {
            return languageRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Language add(StringRequest request) {
        try {
            if (languageRepository.existsByName(request.getText().trim())) {
                throw new DuplicateRecordException("Name was used");
            }
            Language language = new Language();
            language.setName(request.getText().trim());
            language = languageRepository.save(language);
            return language;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public Language update(int id, StringRequest request) {
        try {
            Optional<Language> language = languageRepository.findById(id);
            if (language.isPresent() && request.getText() != null && !language.get().getName().equals(request.getText().trim())) {
                if (languageRepository.existsByName(request.getText().trim()))
                    throw new DuplicateRecordException("Name was used");
                language.get().setName(request.getText().trim());
                return languageRepository.save(language.get());
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean delete(int id) {
        try {
            Optional<Language> language = languageRepository.findById(id);
            if (language.isPresent()) {
                languageRepository.delete(language.get());
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }
}
