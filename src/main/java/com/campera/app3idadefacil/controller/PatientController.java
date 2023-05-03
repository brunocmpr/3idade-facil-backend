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

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE } )
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
    /*
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE } )
    @Operation(summary = "Register new drug")
    public ResponseEntity<DrugDto> createDrug(
            @RequestPart(value = "drugForm", required = true) DrugForm drugForm
            , @RequestPart(value = "images", required = false) @Size(max = 4) Optional<List<MultipartFile>> images
            , Authentication authentication ){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        Drug drug = service.createDrug(drugForm, appUser, images);
        DrugDto drugDto = new DrugDto(drug);
        return ResponseEntity.ok(drugDto);
    }
     */

    @GetMapping
    @Operation(summary = "List patients by admin")
    public ResponseEntity<List<PatientDto>> listAll(Authentication authentication){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        List<Patient> patients = service.findAllByAdmin(appUser);
        List<PatientDto> dtos = PatientMapper.convertToDto(patients);
        return ResponseEntity.ok(dtos);
    }
}
