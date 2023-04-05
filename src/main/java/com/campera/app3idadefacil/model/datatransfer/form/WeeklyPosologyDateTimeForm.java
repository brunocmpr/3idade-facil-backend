package com.campera.app3idadefacil.model.datatransfer.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class WeeklyPosologyDateTimeForm {
    @NotNull
    private DayOfWeek dayOfWeek;
    @NotNull
    private LocalTime time;
}
