package com.campera.app3idadefacil.controller;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.DrugPlan;
import com.campera.app3idadefacil.model.datatransfer.dto.DrugPlanDto;
import com.campera.app3idadefacil.model.datatransfer.form.CustomPosologyForm;
import com.campera.app3idadefacil.service.CustomPosologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customposology")
@SecurityRequirement(name = "bearerAuth")
public class CustomPosologyController {

    @Autowired
    private CustomPosologyService service;

    @PutMapping("/{drugPlanId}")
    @Operation(summary = "Replace list of custom posologies for a drug plan")
    public ResponseEntity<DrugPlanDto> replaceCustomPosologies(@PathVariable Long drugPlanId
            , @RequestBody List<CustomPosologyForm> customPosologiesForm, Authentication authentication){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        DrugPlan plan = service.replaceCustomPosologies(drugPlanId, customPosologiesForm, appUser);
        DrugPlanDto dto = new DrugPlanDto(plan);
        return ResponseEntity.ok(dto);
    }

}
