package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.WeeklyPosologyDateTime;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class WeeklyPosologyDateTimeDto {
    private DayOfWeek dayOfWeek;
    private LocalTime time;

    public WeeklyPosologyDateTimeDto(WeeklyPosologyDateTime weeklyPosologyDateTime){
        this.dayOfWeek = weeklyPosologyDateTime.getDayOfWeek();
        this.time = weeklyPosologyDateTime.getTime();
    }
}
