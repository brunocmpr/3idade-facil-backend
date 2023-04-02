package com.campera.app3idadefacil.model.datatransfer.form;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class WeeklyPosologyForm {
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private List<WeeklyPosologyDateTimeForm> weeklyPosologyDateTimes;
}
