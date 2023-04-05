package com.campera.app3idadefacil.model.datatransfer.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {
    @NotNull @NotEmpty @Size(min = 2, max= 150)  @Getter @Setter
    private String email;
    @NotNull @NotEmpty @Size(min = 2, max= 150)  @Getter @Setter
    private String password;

    public UsernamePasswordAuthenticationToken convertToUserPwToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}