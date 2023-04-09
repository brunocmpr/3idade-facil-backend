package com.campera.app3idadefacil.repository;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByCaretaker(AppUser appUser);
    List<Drug> findByNameIgnoreCaseAndStrengthIgnoreCaseAndCaretaker(String name, String strength, AppUser appUser);
}
