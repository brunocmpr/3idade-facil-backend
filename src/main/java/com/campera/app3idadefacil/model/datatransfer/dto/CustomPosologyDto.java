package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.CustomPosology;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CustomPosologyDto {
    private ZonedDateTime zonedDateTime;

    public  CustomPosologyDto(CustomPosology customPosology){
        this.zonedDateTime = customPosology.getZonedDateTime();
    }
}
