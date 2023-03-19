package com.campera.app3idadefacil.controller;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Patient;
import com.campera.app3idadefacil.model.datatransfer.dto.AppUserDto;
import com.campera.app3idadefacil.model.datatransfer.dto.PatientDto;
import com.campera.app3idadefacil.model.datatransfer.form.AppUserForm;
import com.campera.app3idadefacil.model.datatransfer.form.PatientForm;
import com.campera.app3idadefacil.model.datatransfer.mapper.PatientMapper;
import com.campera.app3idadefacil.service.AppUserService;
import com.campera.app3idadefacil.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearerAuth")
public class PatientController {
    @Autowired
    PatientService service;

    @PostMapping
    @Operation(summary = "Register new patient")
    public ResponseEntity<PatientDto> register(@RequestBody @Valid PatientForm form,
                                               Authentication authentication) {
        try {
            AppUser appUser = (AppUser) authentication.getPrincipal();
            Patient patient = service.create(form, appUser);
            PatientDto dto = PatientMapper.convertToDto(patient);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
