package com.campera.app3idadefacil.repository;

import com.campera.app3idadefacil.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
