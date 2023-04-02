package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.DrugPlan;
import com.campera.app3idadefacil.model.PosologyType;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DrugPlanDto {
    private Long id;
    private Long drugId;
    private String drugName;
    private String strength;
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientNickname;
    private PosologyType posologyType;
    private UniformPosologyDto uniformPosologyDto;
    private WeeklyPosologyDto weeklyPosologyDto;
    private List<CustomPosologyDto> customPosologyDto;

    public DrugPlanDto(DrugPlan plan) {
        this.id = plan.getId();
        this.drugId = plan.getDrug().getId();
        this.drugName = plan.getDrug().getName();
        this.strength = plan.getStrength();
        this.patientId = plan.getPatient().getId();
        this.patientFirstName = plan.getPatient().getFirstName();
        this.patientLastName = plan.getPatient().getLastName();
        this.patientNickname = plan.getPatient().getNickname();
        this.posologyType = plan.getPosologyType();
        this.uniformPosologyDto = plan.getPosologyType().equals(PosologyType.UNIFORM)
                ? new UniformPosologyDto(plan.getUniformPosology())
                : null;
        this.weeklyPosologyDto = plan.getPosologyType().equals(PosologyType.WEEKLY)
                ? new WeeklyPosologyDto(plan.getWeeklyPosology())
                : null;
        this.customPosologyDto = plan.getPosologyType().equals(PosologyType.CUSTOM)
                ? plan.getCustomPosologies().stream().map(CustomPosologyDto::new).collect(Collectors.toList())
                : null;
    }
}
