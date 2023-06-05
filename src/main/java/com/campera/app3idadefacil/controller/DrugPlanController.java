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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    @Operation(summary = "List drug plans by admin")
    public ResponseEntity<List<DrugPlanDto>> listAll(Authentication authentication){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        List<DrugPlan> plans = service.findAllByAdmin(appUser);
        List<DrugPlanDto> dtos = plans.stream().map(DrugPlanDto::new).toList();
        return ResponseEntity.ok(dtos);

    }

    @DeleteMapping
    @Operation(summary = "Delete drug plan by Id")
    public ResponseEntity<DrugPlanDto> deleteDrugPlan(@RequestParam Long id, Authentication authentication){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        DrugPlan plan = service.deleteDrugPlan(id, appUser);
        DrugPlanDto dto = new DrugPlanDto(plan);
        return ResponseEntity.ok(dto);
    }
}
