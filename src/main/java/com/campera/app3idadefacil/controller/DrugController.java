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

@RestController
@RequestMapping("/drug")
@SecurityRequirement(name = "bearerAuth")
public class DrugController {
    @Autowired
    private DrugService service;

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
            @RequestPart("drugForm") DrugForm drugForm
            , @RequestPart(value = "images", required = false) @Size(max = 4) List<MultipartFile> images
            , Authentication authentication ){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        Drug drug = service.createDrug(drugForm, appUser, images);
        DrugDto drugDto = new DrugDto(drug);
        return ResponseEntity.ok(drugDto);
    }
}
