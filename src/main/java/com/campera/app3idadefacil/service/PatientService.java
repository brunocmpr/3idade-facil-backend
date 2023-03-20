package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Patient;
import com.campera.app3idadefacil.model.datatransfer.form.PatientForm;
import com.campera.app3idadefacil.model.datatransfer.mapper.PatientMapper;
import com.campera.app3idadefacil.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public Patient create(PatientForm form, AppUser appUser) {
        Patient patient = PatientMapper.convertFromForm(form, appUser);
        patient = repository.save(patient);
        return patient;
    }

    public List<Patient> findAllByAdmin(AppUser appUser) {
        List<Patient> patients = repository.findAllByAdmin(appUser);
        return patients;
    }
}
