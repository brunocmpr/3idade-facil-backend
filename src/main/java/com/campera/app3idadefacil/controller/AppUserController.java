package com.campera.app3idadefacil.controller;

import com.campera.app3idadefacil.model.datatransfer.dto.AppUserDto;
import com.campera.app3idadefacil.model.datatransfer.form.AppUserForm;
import com.campera.app3idadefacil.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appuser")
public class AppUserController {
    @Autowired
    AppUserService service;

    @PostMapping
    public ResponseEntity<AppUserDto> register(AppUserForm form){
        try {
            AppUserDto appUserDto = service.register(form);
            return ResponseEntity.ok(appUserDto);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
