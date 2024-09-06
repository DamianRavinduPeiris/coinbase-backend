package com.damian.coinbase.advisor;

import com.damian.coinbase.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@CrossOrigin
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public ResponseEntity<Response> handleExceptions(Exception e) {
        return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server threw an error : " + e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
