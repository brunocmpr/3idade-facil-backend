package com.campera.app3idadefacil.model.datatransfer.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
public class CustomPosologyForm {
    @NotNull
    private ZonedDateTime zonedDateTime;
}
