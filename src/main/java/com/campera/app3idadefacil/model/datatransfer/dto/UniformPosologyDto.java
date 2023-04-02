package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.TimeUnit;
import com.campera.app3idadefacil.model.UniformPosology;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UniformPosologyDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer timeLength;
    private TimeUnit timeUnit;

    public UniformPosologyDto(UniformPosology uniformPosology) {
        this.startDate = uniformPosology.getStartDatetime();
        this.endDate = uniformPosology.getEndDatetime();
        this.timeLength = uniformPosology.getTimeLength();
        this.timeUnit = uniformPosology.getTimeUnit();
    }
}
