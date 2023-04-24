package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.CustomPosology;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CustomPosologyDto {
    private Long id;
    private LocalDateTime dateTime;

    public  CustomPosologyDto(CustomPosology customPosology){
        this.id = customPosology.getId();
        this.dateTime = customPosology.getDateTime();
    }
}
