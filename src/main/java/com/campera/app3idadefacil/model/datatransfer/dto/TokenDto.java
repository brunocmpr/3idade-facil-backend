package com.campera.app3idadefacil.model.datatransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TokenDto {
    @Getter
    private String accessToken;
    @Getter
    private String tokenType;
}
