package com.campera.app3idadefacil.model.datatransfer.form;

import com.campera.app3idadefacil.model.CustomPosology;
import com.campera.app3idadefacil.model.PosologyType;
import com.campera.app3idadefacil.model.WeeklyPosology;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class DrugPlanForm {
    @NotNull
    private Long drugId;
    @NotNull
    private Long patientId;
    @NotNull
    private PosologyType posologyType;
    private UniformPosologyForm uniformPosology;
    private WeeklyPosologyForm weeklyPosology;
    @Size(min = 1)
    private List<CustomPosologyForm> customPosologies;

    @AssertTrue
    public boolean atLeastOnePosologyProvided(){
        return uniformPosology != null || weeklyPosology != null || customPosologies != null;
    }
}
