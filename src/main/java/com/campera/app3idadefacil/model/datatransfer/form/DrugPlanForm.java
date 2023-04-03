package com.campera.app3idadefacil.model.datatransfer.form;

import com.campera.app3idadefacil.model.CustomPosology;
import com.campera.app3idadefacil.model.PosologyType;
import com.campera.app3idadefacil.model.WeeklyPosology;
import lombok.Data;

import java.util.List;

@Data
public class DrugPlanForm {
    private Long drugId;
    private Long patientId;
    private PosologyType posologyType;
    private UniformPosologyForm uniformPosology;
    private WeeklyPosologyForm weeklyPosology;
    private List<CustomPosologyForm> customPosologies;
}
