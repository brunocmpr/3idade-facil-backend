package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.TimeUnit;
import com.campera.app3idadefacil.model.UniformPosology;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UniformPosologyDto {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer timeLength;
    private TimeUnit timeUnit;

    public UniformPosologyDto(UniformPosology uniformPosology) {
        this.startDateTime = uniformPosology.getStartDateTime();
        this.endDateTime = uniformPosology.getEndDateTime();
        this.timeLength = uniformPosology.getTimeLength();
        this.timeUnit = uniformPosology.getTimeUnit();
    }
}
