package com.example.book_shopping.controller;

import com.example.book_shopping.request.ProcedureRequest;
import com.example.book_shopping.service.ProcedureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lengo
 * created on 3/18/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/procedures")
public class ProcedureController {
    private final ProcedureService service;
    Logger logger = LoggerFactory.getLogger(ProcedureController.class);

    public ProcedureController(ProcedureService service) {
        this.service = service;
    }

    @GetMapping("/{procedureId}")
    public ResponseEntity<Object> getProcedure(@PathVariable("procedureId") int procedureId) {
        return ResponseEntity.ok(service.get(procedureId));
    }

    @PutMapping("/{procedureId}")
    public ResponseEntity<Object> updateProcedure(@PathVariable("procedureId") int procedureId, @RequestBody ProcedureRequest request) {
        return ResponseEntity.ok(service.update(procedureId, request));
    }

    @PostMapping("")
    public ResponseEntity<Object> addProcedure(@RequestBody ProcedureRequest request) {
        return ResponseEntity.ok(service.add(request));
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllProcedure() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{procedureId}")
    public ResponseEntity<Object> deleteProcedure(@PathVariable("procedureId") int procedureId) {
        if (service.delete(procedureId)) return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
