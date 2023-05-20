package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.Image;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DrugDto {
    private Long id;
    private String name;
    private String strength;
    private String instructions;

    private List<Long> imageIds;

    public DrugDto(Drug drug){
        this.id = drug.getId();
        this.name = drug.getName();
        this.strength = drug.getStrength();
        this.imageIds = drug.getImages().stream().map(Image::getId).collect(Collectors.toList());
        this.instructions = drug.getInstructions();
    }
}