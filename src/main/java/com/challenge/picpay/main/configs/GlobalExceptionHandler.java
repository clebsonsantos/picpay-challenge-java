package com.challenge.picpay.main.configs;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.challenge.picpay.core.usecases.transaction.TransactionException;
import com.challenge.picpay.core.usecases.user.UserException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserException.class)
    ResponseEntity<ApiResponseException> handleUserException(UserException ex) {
        return new ResponseEntity<>(new ApiResponseException(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionException.class)
    ResponseEntity<ApiResponseException> handleTransactionException(TransactionException ex) {
        return new ResponseEntity<>(new ApiResponseException(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<ApiResponseException> handleDuplicatedException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(new ApiResponseException(false, "Cadastro já existe!"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponseException> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(new ApiResponseException(false, "Ocorreu um erro interno em nossos servidores."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}