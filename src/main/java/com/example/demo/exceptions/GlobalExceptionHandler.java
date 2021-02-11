package com.example.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * No resource found for the specified req. param ID
     * @param ex
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiExceptionError err = new ApiExceptionError(LocalDateTime.now(), HttpStatus.NOT_FOUND, "Resource Not Found" ,details);

        return new ResponseEntity(err, err.getStatus());
    }

    /**
     * will execute when Validation(@Valid) fails for req. parameters
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request){

        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult().getFieldErrors().stream().
                map(err -> err.getObjectName() + ": " + err.getDefaultMessage()).collect(Collectors.toList());

        ApiExceptionError err = new ApiExceptionError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Validation Errors", details);

        return new ResponseEntity<>(err, err.getStatus());

    }




}
