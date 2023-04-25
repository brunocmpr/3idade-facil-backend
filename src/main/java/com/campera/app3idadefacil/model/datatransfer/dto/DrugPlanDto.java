package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.DrugPlan;
import com.campera.app3idadefacil.model.PosologyType;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DrugPlanDto {
    private Long id;
    private DrugDto drug;
    private PatientDto patient;
    private PosologyType posologyType;
    private UniformPosologyDto uniformPosology;
    private WeeklyPosologyDto weeklyPosology;
    private List<CustomPosologyDto> customPosology;

    public DrugPlanDto(DrugPlan plan) {
        this.id = plan.getId();
        this.drug = new DrugDto(plan.getDrug());
        this.patient = new PatientDto(plan.getPatient());
        this.posologyType = plan.getPosologyType();
        this.uniformPosology = plan.getPosologyType().equals(PosologyType.UNIFORM)
                ? new UniformPosologyDto(plan.getUniformPosology())
                : null;
        this.weeklyPosology = plan.getPosologyType().equals(PosologyType.WEEKLY)
                ? new WeeklyPosologyDto(plan.getWeeklyPosology())
                : null;
        this.customPosology = plan.getPosologyType().equals(PosologyType.CUSTOM)
                ? plan.getCustomPosologies().stream().map(CustomPosologyDto::new).collect(Collectors.toList())
                : null;
    }
}
