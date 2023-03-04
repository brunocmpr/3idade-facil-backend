package com.campera.app3idadefacil.model.datatransfer.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AppUserForm {
    @NotNull @NotEmpty @Size(min = 2)  @Getter @Setter
    private String firstName;
    @NotNull @NotEmpty @Size(min = 2)  @Getter @Setter
    private String lastName;
    @NotNull @NotEmpty @Size(min = 1, max = 4) @Pattern(regexp = "^\\d{1,4}$")  @Getter @Setter
    private String countryCode;
    @NotNull @NotEmpty @Size(min = 1, max = 4) @Pattern(regexp = "^\\d{1,4}$")  @Getter @Setter
    private String areaCode;
    @NotNull @NotEmpty @Size(min = 1, max = 10) @Pattern(regexp = "^\\d{1,10}$")  @Getter @Setter
    private String phoneNumber;
    @NotNull @NotEmpty @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")  @Getter	@Setter
    private String email;
    @NotNull @NotEmpty @Pattern(regexp = "^[a-zA-Z\\d~`´!@#$%^&*()_\\-+={\\[}\\]\\|\\:;\"'<,>.?\\/]{6,40}$")
    @Size(min = 1, max = 40) @Getter @Setter
    private String rawPassword;
}
