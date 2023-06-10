package com.campera.app3idadefacil.controller;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.DrugPlan;
import com.campera.app3idadefacil.model.datatransfer.dto.DrugPlanDto;
import com.campera.app3idadefacil.model.datatransfer.form.UniformPosologyForm;
import com.campera.app3idadefacil.service.UniformPosologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uniformposology")
@SecurityRequirement(name = "bearerAuth")
public class UniformPosologyController {

    @Autowired
    private UniformPosologyService service;

    @PutMapping("/{drugPlanId}")
    @Operation(summary = "Update uniform posology for a drug plan")
    public ResponseEntity<DrugPlanDto> updateUniformPosology(@PathVariable Long drugPlanId
            , @RequestBody UniformPosologyForm uniformPosologyForm, Authentication authentication){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        DrugPlan plan = service.update(drugPlanId, uniformPosologyForm, appUser);
        DrugPlanDto dto = new DrugPlanDto(plan);
        return ResponseEntity.ok(dto);
    }
}
