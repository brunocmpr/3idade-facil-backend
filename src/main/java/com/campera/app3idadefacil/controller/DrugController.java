package com.campera.app3idadefacil.controller;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.dto.DrugDto;
import com.campera.app3idadefacil.service.DrugService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<DrugDto> drugDtos = drugs.stream().map(DrugDto::new).toList();
        return ResponseEntity.ok(drugDtos);
    }


}
