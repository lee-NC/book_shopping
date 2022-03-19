package com.example.book_shopping.service;

import com.example.book_shopping.entity.Procedure;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.DuplicateRecordException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.ProcedureRepository;
import com.example.book_shopping.request.ProcedureRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lengo
 * created on 3/19/2022
 */
@Service
public class ProcedureService {
    private ProcedureRepository procedureRepository;

    public List<Procedure> getAll() {
        try {
            return procedureRepository.findAll();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Procedure get(int id) {
        try {
            Optional<Procedure> procedure = procedureRepository.findById(id);
            if (procedure.isPresent()) return procedure.get();
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Procedure add(ProcedureRequest request) {
        try {
            if (procedureRepository.existsByName(request.getName().trim()))
                throw new DuplicateRecordException("Name was used");
            Procedure procedure = new Procedure();
            if (request.getCountry() != null) {
                procedure.setCountry(request.getCountry().trim());
            }
            procedure.setName(request.getName().trim());
            return procedureRepository.save(procedure);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Procedure update(int id, ProcedureRequest request) {
        try {
            Optional<Procedure> procedure = procedureRepository.findById(id);
            if (procedure.isPresent()) {
                if (request.getCountry() != null && !procedure.get().getCountry().equals(request.getCountry().trim())) {
                    if (procedureRepository.existsByName(request.getName().trim()))
                        throw new DuplicateRecordException("Name was used");
                    procedure.get().setCountry(request.getCountry().trim());

                }
                if (request.getName() != null && !procedure.get().getName().equals(request.getName().trim())) {
                    procedure.get().setCountry(request.getCountry().trim());

                }
                return procedureRepository.save(procedure.get());
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean delete(int id) {
        try {
            Optional<Procedure> procedure = procedureRepository.findById(id);
            if (procedure.isPresent()) {
                procedureRepository.delete(procedure.get());
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
