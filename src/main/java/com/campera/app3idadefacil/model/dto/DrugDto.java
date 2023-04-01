package com.campera.app3idadefacil.model.dto;

import com.campera.app3idadefacil.model.Drug;
import lombok.Data;

@Data
public class DrugDto {
    private Long id;
    private String name;

    public DrugDto(Drug drug){
        this.id = drug.getId();
        this.name = drug.getName();
    }
}
