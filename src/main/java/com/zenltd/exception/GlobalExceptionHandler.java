package com.zenltd.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // Exception Handlers Here
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex){

        ApiError apiError = new ApiError.
                Builder()
                .withMessage(ex.getMessage())
                .withHttpStatus(HttpStatus.NOT_FOUND)
                .withCreatedAt()
                .build();
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidInputsException(MethodArgumentNotValidException exception){
        List<String> errorMessages = new ArrayList<>();
        // Collect all validation error messages in list
        List<FieldError> listOfValidationErrors = exception.getBindingResult().getFieldErrors();
        for (FieldError fieldError: listOfValidationErrors) {
            errorMessages.add(fieldError.getDefaultMessage());
        }
        // If no validation errors were captured, provide a fallback error message
        if (errorMessages.isEmpty()) {
            errorMessages.add("No specific validation errors found.");
        }

        ApiError apiError = new ApiError
                .Builder()
                .withMessage("Validation failed for input data")
                .withHttpStatus(HttpStatus.BAD_REQUEST)
                .withCreatedAt()
                .withMessages(errorMessages) // Set the validation error messages of fields
                .build();

        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }
    // Generalized Exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleGenericException(Exception exception){
//        ApiError apiError = new ApiError.
//                Builder()
//                .withMessage("Some Error Occurred")
//                .withHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//                .withCreatedAt()
//                .build();
//
//        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
//    }


}