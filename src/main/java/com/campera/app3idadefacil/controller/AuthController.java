package com.campera.app3idadefacil.controller;

import com.campera.app3idadefacil.config.security.TokenService;
import com.campera.app3idadefacil.model.datatransfer.dto.TokenDto;
import com.campera.app3idadefacil.model.datatransfer.form.LoginForm;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Log into system, receive jwt")
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm loginForm){
        UsernamePasswordAuthenticationToken loginData = loginForm.convertToUserPwToken();
        try {
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);
            System.out.println(token);
            TokenDto tokenDto = new TokenDto(token, "Bearer");
            return ResponseEntity.ok().body(tokenDto);
        }catch(AuthenticationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
