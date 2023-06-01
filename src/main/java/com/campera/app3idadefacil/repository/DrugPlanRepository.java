package com.campera.app3idadefacil.repository;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.DrugPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugPlanRepository extends JpaRepository<DrugPlan, Long> {
    List<DrugPlan> findAllByPatient_Admin(AppUser appUser);
}
