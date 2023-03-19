package com.campera.app3idadefacil.model.datatransfer.mapper;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Patient;
import com.campera.app3idadefacil.model.datatransfer.dto.AppUserDto;
import com.campera.app3idadefacil.model.datatransfer.dto.PatientDto;
import com.campera.app3idadefacil.model.datatransfer.form.AppUserForm;
import com.campera.app3idadefacil.model.datatransfer.form.PatientForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PatientMapper {
    public static PatientDto convertToDto(Patient patient){
        return new PatientDto(patient.getId(), patient.getAdmin().getId(), patient.getFirstName(), patient.getLastName()
                , patient.getNickname());
    }

    public static Patient convertFromForm(PatientForm form, AppUser admin){
        return new Patient(admin, form.getFirstName(), form.getLastName(), form.getNickname());
    }
}
