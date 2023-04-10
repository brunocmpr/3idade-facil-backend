package com.campera.app3idadefacil.model.datatransfer.mapper;

import com.campera.app3idadefacil.model.*;
import com.campera.app3idadefacil.model.datatransfer.form.DrugPlanForm;

import java.util.stream.Collectors;

public class DrugPlanMapper {
    public static DrugPlan fromForm(DrugPlanForm planForm, Patient patient, Drug drug) {
        DrugPlan drugPlan = new DrugPlan();
        drugPlan.setPosologyType(planForm.getPosologyType());
        drugPlan.setDrug(drug);
        drugPlan.setPatient(patient);

        switch (drugPlan.getPosologyType()) {
            case UNIFORM -> {
                drugPlan.setUniformPosology(new UniformPosology(planForm.getUniformPosology()));
                drugPlan.getUniformPosology().setDrugPlan(drugPlan);
                drugPlan.setCustomPosologies(null);
                drugPlan.setWeeklyPosology(null);
                break;
            }
            case WEEKLY -> {
                drugPlan.setWeeklyPosology(new WeeklyPosology(planForm.getWeeklyPosology()));
                drugPlan.getWeeklyPosology().setDrugPlan(drugPlan);
                drugPlan.setUniformPosology(null);
                drugPlan.setCustomPosologies(null);
                break;
            }
            case CUSTOM -> {
                drugPlan.setCustomPosologies(planForm.getCustomPosologies().stream().map(CustomPosology::new)
                        .collect(Collectors.toList()));
                drugPlan.getCustomPosologies().stream().forEach(posology -> posology.setDrugPlan(drugPlan));
                drugPlan.setUniformPosology(null);
                drugPlan.setWeeklyPosology(null);
                break;
            }
        }
        return drugPlan;
    }
}
