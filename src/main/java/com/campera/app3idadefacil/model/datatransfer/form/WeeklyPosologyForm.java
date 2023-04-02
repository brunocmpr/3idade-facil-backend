package com.campera.app3idadefacil.model.datatransfer.form;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WeeklyPosologyForm {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<WeeklyPosologyDateTimeForm> weeklyPosologyDateTimes;
}
