package com.example.book_shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author lengo
 * created on 3/18/2022
 */
@ResponseStatus(HttpStatus.LOOP_DETECTED)
public class DuplicateRecordException extends RuntimeException {
    public DuplicateRecordException(String message) {
        super(message);
    }
}
