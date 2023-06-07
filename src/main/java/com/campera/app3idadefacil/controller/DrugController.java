package com.campera.app3idadefacil.controller;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.Image;
import com.campera.app3idadefacil.model.datatransfer.dto.DrugDto;
import com.campera.app3idadefacil.model.datatransfer.form.DrugForm;
import com.campera.app3idadefacil.model.datatransfer.mapper.DrugMapper;
import com.campera.app3idadefacil.service.DrugService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drug")
@SecurityRequirement(name = "bearerAuth")
public class DrugController {
    @Autowired
    private DrugService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get drug by id")
    public ResponseEntity<DrugDto> getDrug(@PathVariable Long id, Authentication authentication){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        Drug drug = service.findById(id, appUser);
        DrugDto drugDto = new DrugDto(drug);
        return ResponseEntity.ok(drugDto);
    }

    @GetMapping
    @Operation(summary = "List drugs by admin")
    public ResponseEntity<List<DrugDto>> listDrugs(Authentication authentication){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        List<Drug> drugs = service.findAllByMainCaretaker(appUser);
        List<DrugDto> drugDtos = DrugMapper.toDtoList(drugs);
        return ResponseEntity.ok(drugDtos);
    }

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

    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Update drug")
    public ResponseEntity<DrugDto> updateDrug(@PathVariable Long id
            , @RequestPart(value = "drugForm", required = true) @Valid DrugForm drugForm
            , @RequestPart(value = "images", required = false) @Size(max = 4) Optional<List<MultipartFile>> images
            , Authentication authentication ){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        Drug drug = service.updateDrug(id, drugForm, appUser, images);
        DrugDto drugDto = new DrugDto(drug);
        return ResponseEntity.ok(drugDto);
    }

    @DeleteMapping
    @Operation(summary = "Delete patient by id")
    public ResponseEntity<DrugDto> deleteDrug(@RequestParam Long id, Authentication authentication){
        Drug drug = service.deleteDrug(id, (AppUser) authentication.getPrincipal());
        DrugDto drugDto = new DrugDto(drug);
        return ResponseEntity.ok(drugDto);
    }
}
