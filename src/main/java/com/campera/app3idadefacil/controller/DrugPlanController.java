package com.campera.app3idadefacil.controller;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.DrugPlan;
import com.campera.app3idadefacil.model.datatransfer.dto.DrugDto;
import com.campera.app3idadefacil.model.datatransfer.dto.DrugPlanDto;
import com.campera.app3idadefacil.model.datatransfer.form.DrugForm;
import com.campera.app3idadefacil.model.datatransfer.form.DrugPlanForm;
import com.campera.app3idadefacil.service.DrugPlanService;
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
@RequestMapping("/plan")
@SecurityRequirement(name = "bearerAuth")
public class DrugPlanController {
    @Autowired
    private DrugPlanService service;

    @PostMapping
    @Operation(summary = "Register new drug plan")
    public ResponseEntity<DrugPlanDto> createDrugPlan(@Valid @RequestBody DrugPlanForm planForm
            , Authentication authentication){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        DrugPlan plan = service.createDrugPlan(planForm, appUser);
        DrugPlanDto dto = new DrugPlanDto(plan);
        return ResponseEntity.ok(dto);
    }
}
