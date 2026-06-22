package com.bank.Auth_Service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Map<String,String> handleException(
            Exception ex){

        Map<String,String> map =
                new HashMap<>();

        map.put("message",
                ex.getMessage());

        return map;
    }
}