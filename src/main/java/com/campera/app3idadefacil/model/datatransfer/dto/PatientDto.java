package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Image;
import com.campera.app3idadefacil.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data @AllArgsConstructor
public class PatientDto {
    private Long id;
    private Long adminId;
    private String firstName;
    private String lastName;
    private String nickname;
    private List<Long> imageIds;

    public PatientDto(Patient patient) {
        this(patient.getId(), patient.getAdmin().getId(), patient.getFirstName(), patient.getLastName()
                , patient.getNickname(), patient.getImages().stream().map(Image::getId).collect(Collectors.toList()));
    }
}
