package com.campera.app3idadefacil.controller;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.DrugPlan;
import com.campera.app3idadefacil.model.datatransfer.dto.DrugPlanDto;
import com.campera.app3idadefacil.model.datatransfer.form.UniformPosologyForm;
import com.campera.app3idadefacil.model.datatransfer.form.WeeklyPosologyForm;
import com.campera.app3idadefacil.service.UniformPosologyService;
import com.campera.app3idadefacil.service.WeeklyPosologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weeklyposology")
@SecurityRequirement(name = "bearerAuth")
public class WeeklyPosologyController {
    @Autowired
    private WeeklyPosologyService service;

    @PutMapping("/{drugPlanId}")
    @Operation(summary = "Update weekly posology for a drug plan")
    public ResponseEntity<DrugPlanDto> updateWeeklyPosology(@PathVariable Long drugPlanId
            , @RequestBody WeeklyPosologyForm uniformPosologyForm, Authentication authentication){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        DrugPlan plan = service.update(drugPlanId, uniformPosologyForm, appUser);
        DrugPlanDto dto = new DrugPlanDto(plan);
        return ResponseEntity.ok(dto);
    }
}
