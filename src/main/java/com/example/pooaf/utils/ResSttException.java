package com.example.pooaf.utils;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ResSttException {
    
    static public Supplier<? extends ResponseStatusException> notFound(String msg) {
        return () -> new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
    }
}
