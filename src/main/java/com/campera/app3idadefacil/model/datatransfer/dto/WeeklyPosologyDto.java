package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.WeeklyPosology;
import com.campera.app3idadefacil.model.WeeklyPosologyDateTime;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class WeeklyPosologyDto {
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private List<WeeklyPosologyDateTimeDto> dateTimeList;

    public WeeklyPosologyDto(WeeklyPosology weeklyPosology) {
        this.startDate = weeklyPosology.getStartDatetime();
        this.endDate = weeklyPosology.getEndDatetime();
        this.dateTimeList = weeklyPosology.getWeeklyPosologyDateTime().stream().map(WeeklyPosologyDateTimeDto::new)
                .collect(Collectors.toList());
    }
}
