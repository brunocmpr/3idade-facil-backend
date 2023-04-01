package com.campera.app3idadefacil.repository;

import com.campera.app3idadefacil.model.DrugPlan;
import com.campera.app3idadefacil.model.WeeklyPosology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyPosologyRepository extends JpaRepository<WeeklyPosology, Long> {
}
