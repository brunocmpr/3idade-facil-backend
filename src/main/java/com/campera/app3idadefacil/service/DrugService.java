package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {
    @Autowired
    private DrugRepository repository;

    public List<Drug> findAllByMainCaretaker(AppUser appUser){
        return repository.findByCaretaker(appUser);
    }
}
