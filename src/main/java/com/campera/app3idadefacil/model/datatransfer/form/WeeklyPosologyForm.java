package com.campera.app3idadefacil.model.datatransfer.form;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class WeeklyPosologyForm {
    @NotNull
    private LocalDateTime startDateTime;
    @NotNull
    private LocalDateTime endDateTime;
    @NotNull @Size(min = 1)
    private List<WeeklyPosologyDateTimeForm> weeklyPosologyDateTimes;
}
