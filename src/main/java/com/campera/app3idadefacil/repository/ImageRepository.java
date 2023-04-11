package com.campera.app3idadefacil.repository;

import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByDrug(Drug drug);
}
