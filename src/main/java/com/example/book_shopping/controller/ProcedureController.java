package com.example.book_shopping.controller;

import com.example.book_shopping.request.ProcedureRequest;
import com.example.book_shopping.service.ProcedureService;
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
    private ProcedureService procedureService;

    @GetMapping("/{procedureId}")
    public ResponseEntity<Object> getProcedure(@PathVariable("procedureId") int procedureId) {
        return ResponseEntity.ok(procedureService.get(procedureId));
    }

    @PutMapping("/{procedureId}")
    public ResponseEntity<Object> updateProcedure(@PathVariable("procedureId") int procedureId, @RequestBody ProcedureRequest request) {
        return ResponseEntity.ok(procedureService.update(procedureId, request));
    }

    @PostMapping("")
    public ResponseEntity<Object> addProcedure(@RequestBody ProcedureRequest request) {
        return ResponseEntity.ok(procedureService.add(request));
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllProcedure() {
        return ResponseEntity.ok(procedureService.getAll());
    }

    @DeleteMapping("/{procedureId}")
    public ResponseEntity<Object> deleteProcedure(@PathVariable("procedureId") int procedureId) {
        if (procedureService.delete(procedureId)) return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
