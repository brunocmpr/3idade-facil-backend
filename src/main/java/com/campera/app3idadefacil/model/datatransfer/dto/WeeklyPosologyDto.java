package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.WeeklyPosology;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class WeeklyPosologyDto {
    private Long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<WeeklyPosologyDateTimeDto> weeklyPosologyDateTimes;

    public WeeklyPosologyDto(WeeklyPosology weeklyPosology) {
        this.id = weeklyPosology.getId();
        this.startDateTime = weeklyPosology.getStartDateTime();
        this.endDateTime = weeklyPosology.getEndDateTime();
        this.weeklyPosologyDateTimes = weeklyPosology.getWeeklyPosologyDateTimes().stream().map(WeeklyPosologyDateTimeDto::new)
                .collect(Collectors.toList());
    }
}
