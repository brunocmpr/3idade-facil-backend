package com.campera.app3idadefacil.model.datatransfer.form;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class DrugForm {
    @NotEmpty @Size(min = 2, max= 150)
    private String name;

    @Size(min = 2, max= 150)
    private String strength;

    private String instructions;
}