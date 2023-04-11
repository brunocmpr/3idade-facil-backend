package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.CustomPosology;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CustomPosologyDto {
    private LocalDateTime dateTime;

    public  CustomPosologyDto(CustomPosology customPosology){
        this.dateTime = customPosology.getDateTime();
    }
}
