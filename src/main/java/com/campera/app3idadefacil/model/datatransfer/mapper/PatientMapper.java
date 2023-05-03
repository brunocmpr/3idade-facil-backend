package com.campera.app3idadefacil.model.datatransfer.mapper;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Image;
import com.campera.app3idadefacil.model.Patient;
import com.campera.app3idadefacil.model.datatransfer.dto.AppUserDto;
import com.campera.app3idadefacil.model.datatransfer.dto.PatientDto;
import com.campera.app3idadefacil.model.datatransfer.form.AppUserForm;
import com.campera.app3idadefacil.model.datatransfer.form.PatientForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class PatientMapper {
    public static PatientDto convertToDto(Patient patient){
        return new PatientDto(patient.getId(), patient.getAdmin().getId(), patient.getFirstName(), patient.getLastName()
                , patient.getNickname(), patient.getImages().stream().map(Image::getId).collect(Collectors.toList()));
    }

    public static List<PatientDto> convertToDto(List<Patient> patients){
        return patients.stream().map(PatientMapper::convertToDto).collect(Collectors.toList());
    }

    public static Patient convertFromForm(PatientForm form, AppUser admin){
        return new Patient(admin, form.getFirstName(), form.getLastName(), form.getNickname());
    }
}
