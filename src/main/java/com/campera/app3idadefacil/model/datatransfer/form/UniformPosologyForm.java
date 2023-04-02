package com.campera.app3idadefacil.model.datatransfer.form;

import com.campera.app3idadefacil.model.TimeUnit;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UniformPosologyForm {
    @NotNull
    private LocalDateTime startDateTime;
    @NotNull
    private LocalDateTime endDateTime;
    @NotNull @Min(1)
    private Integer timeLength;
    @NotNull
    private TimeUnit timeUnit;
}
