package com.map_properties.spring_server.response;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.map_properties.spring_server.exception.LoginException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler // when Invalid Credentials
    private ResponseEntity<ErrorMessage> handleInvalidCredentialsException(
            BadCredentialsException ex) {
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage("Invalid credentials", HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler // when login failed
    private ResponseEntity<ErrorMessage> handleLoginException(
            LoginException ex) {
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler // Invalid validation requests
    private ResponseEntity<ErrorValidationResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        Map<String, Object> errors = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), new String[] { error.getDefaultMessage() });
        }

        ErrorValidationResponse response = new ErrorValidationResponse(errors);
        return new ResponseEntity<ErrorValidationResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler // other ExceptionHandler
    private ResponseEntity<ErrorMessage> handleOthersException(
            Exception ex) {
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Getter
    @Setter
    class ErrorMessage {
        private String message;
        private Integer status;

        public ErrorMessage(String message) {
            this.message = message;
            this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }

        public ErrorMessage(String message, Integer status) {
            this.message = message;
            this.status = status;
        }
    }

    @Getter
    @Setter
    class ErrorValidationResponse extends ErrorMessage {
        private Object errors;

        public ErrorValidationResponse(Map<String, Object> errors) {
            super("Validation failed!", HttpStatus.UNPROCESSABLE_ENTITY.value());
            this.errors = errors;
        }
    }
}
