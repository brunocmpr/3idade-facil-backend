package com.campera.app3idadefacil.model.datatransfer.form;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.OffsetTime;

@Data
public class WeeklyPosologyDateTimeForm {
    private DayOfWeek dayOfWeek;
    private OffsetTime offsetTime;
}
