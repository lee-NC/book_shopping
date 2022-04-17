package com.example.book_shopping.controller;

import com.example.book_shopping.request.CreatePublisherRequest;
import com.example.book_shopping.request.UpdatePublisherRequest;
import com.example.book_shopping.service.PublisherService;
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
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService service;
    Logger logger = LoggerFactory.getLogger(PublisherController.class);

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<Object> getPublisher(@PathVariable("publisherId") int publisherId) {
        return ResponseEntity.ok(service.get(publisherId));
    }

    @PutMapping("/{publisherId}")
    public ResponseEntity<Object> updatePublisher(@PathVariable("publisherId") int publisherId, @Valid @RequestBody UpdatePublisherRequest request) {
        return ResponseEntity.ok(service.update(publisherId, request));
    }

    @PostMapping("")
    public ResponseEntity<Object> addPublisher(@Valid @RequestBody CreatePublisherRequest request) {
        return ResponseEntity.ok(service.add(request));
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllPublisher() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{publisherId}")
    public ResponseEntity<Object> deletePublisher(@PathVariable("publisherId") int publisherId) {
        return service.delete(publisherId) ? ResponseEntity.ok(HttpStatus.OK.getReasonPhrase()) : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
