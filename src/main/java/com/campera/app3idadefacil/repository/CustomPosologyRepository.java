package com.campera.app3idadefacil.repository;

import com.campera.app3idadefacil.model.CustomPosology;
import com.campera.app3idadefacil.model.CustomPosologyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomPosologyRepository extends JpaRepository<CustomPosology, CustomPosologyId> {
}
