package com.campera.app3idadefacil.model.datatransfer.mapper;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.datatransfer.dto.DrugDto;
import com.campera.app3idadefacil.model.datatransfer.form.DrugForm;

import java.util.List;

public class DrugMapper {

    public static Drug fromForm(DrugForm drugForm, AppUser appUser) {
        return new Drug(appUser, drugForm.getName(), drugForm.getStrength(), drugForm.getInstructions());
    }

    public static List<DrugDto> toDtoList(List<Drug> drugs) {
        return drugs.stream().map(DrugDto::new).toList();
    }
}
