package com.campera.app3idadefacil.model.datatransfer.dto;

import com.campera.app3idadefacil.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @AllArgsConstructor
public class PatientDto {
    private Long id;
    private Long adminId;
    private String firstName;
    private String lastName;
    private String nickname;
}
