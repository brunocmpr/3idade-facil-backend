package com.campera.app3idadefacil.controller.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FormValidationErrorDto {
    private String field;
    private String errorMessage;
}