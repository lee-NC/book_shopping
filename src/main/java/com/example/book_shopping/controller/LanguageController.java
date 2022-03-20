package com.example.book_shopping.controller;

import com.example.book_shopping.request.StringRequest;
import com.example.book_shopping.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lengo
 * created on 3/18/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/languages")
public class LanguageController {
    private final LanguageService service;
    Logger logger = LoggerFactory.getLogger(LanguageController.class);

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @PutMapping("/{languageId}")
    public ResponseEntity<Object> updateLanguage(@PathVariable("languageId") int languageId, @Valid @RequestBody StringRequest request) {
        return ResponseEntity.ok(service.update(languageId, request));
    }

    @PostMapping("")
    public ResponseEntity<Object> addLanguage(@Valid @RequestBody StringRequest request) {
        return ResponseEntity.ok(service.add(request));
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllLanguage() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<Object> deleteLanguage(@PathVariable("languageId") int languageId) {
        return service.delete(languageId) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
