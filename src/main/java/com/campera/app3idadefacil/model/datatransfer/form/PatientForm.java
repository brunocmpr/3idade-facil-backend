package com.campera.app3idadefacil.model.datatransfer.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PatientForm {
    @NotNull @Size(min = 2, max = 150) @Getter @Setter
    private String firstName;
    @NotNull @Size(min = 2, max = 150) @Getter @Setter
    private String lastName;
    @NotNull @Size(min = 2, max = 150) @Getter @Setter
    private String nickname;
}
