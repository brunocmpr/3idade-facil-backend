package com.campera.app3idadefacil.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class FormValidationHandler {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FormValidationErrorDto> handle(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrorLists = exception.getBindingResult().getFieldErrors();

        List<FormValidationErrorDto> dtoList = fieldErrorLists.stream().map(fieldError -> {
            String errorMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            FormValidationErrorDto errorDto = new FormValidationErrorDto(fieldError.getField(), errorMessage);
            return errorDto;
        }).collect(Collectors.toList());

        return dtoList;
    }
}
