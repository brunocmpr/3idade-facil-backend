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
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearerAuth")
public class PatientController {
    @Autowired
    PatientService service;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Register new patient")
    public ResponseEntity<PatientDto> register(@RequestPart(value="form") @Valid PatientForm form,
                                               @RequestPart(value="images") Optional<List<MultipartFile>> images,
                                               Authentication authentication) {
        try {
            AppUser appUser = (AppUser) authentication.getPrincipal();
            Patient patient = service.create(form, appUser, images);
            PatientDto dto = PatientMapper.convertToDto(patient);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Update patient")
    public ResponseEntity<PatientDto> update(@PathVariable Long id, @RequestPart(value="form") @Valid PatientForm form,
            @RequestPart(value="images") Optional<List<MultipartFile>> images, Authentication authentication) {
        try {
            AppUser appUser = (AppUser) authentication.getPrincipal();
            Patient patient = service.update(id, form, appUser, images);
            PatientDto dto = PatientMapper.convertToDto(patient);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping
    @Operation(summary = "List patients by admin")
    public ResponseEntity<List<PatientDto>> listAll(Authentication authentication){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        List<Patient> patients = service.findAllByAdmin(appUser);
        List<PatientDto> dtos = PatientMapper.convertToDto(patients);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get patient by id")
    public ResponseEntity<PatientDto> getById(@PathVariable Long id, Authentication authentication){
        Patient patient = service.findById(id, (AppUser) authentication.getPrincipal());
        PatientDto dto = PatientMapper.convertToDto(patient);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Operation(summary = "Delete patient by id")
    public ResponseEntity<PatientDto> delete(@RequestParam Long id, Authentication authentication){
        Patient patient = service.delete(id, (AppUser) authentication.getPrincipal());
        PatientDto dto = PatientMapper.convertToDto(patient);
        return ResponseEntity.ok(dto);
    }
}
