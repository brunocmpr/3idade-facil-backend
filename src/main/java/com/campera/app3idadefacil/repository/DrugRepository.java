package com.campera.app3idadefacil.repository;

import com.campera.app3idadefacil.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}
