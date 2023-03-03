package com.campera.app3idadefacil.model.datatransfer.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginForm {
    @NotNull @NotEmpty  @Getter @Setter
    private String email;
    @NotNull @NotEmpty  @Getter @Setter
    private String password;

    public UsernamePasswordAuthenticationToken convertToUserPwToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}