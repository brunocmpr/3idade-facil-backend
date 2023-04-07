package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.model.CustomPosology;
import com.campera.app3idadefacil.repository.CustomPosologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomPosologyService {
    @Autowired
    private CustomPosologyRepository repository;

    public List<CustomPosology> saveAll(List<CustomPosology> posologies){
        return repository.saveAll(posologies);
    }
}
