package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.exception.ExistingEntityException;
import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.datatransfer.mapper.DrugMapper;
import com.campera.app3idadefacil.model.datatransfer.form.DrugForm;
import com.campera.app3idadefacil.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrugService {
    @Autowired
    private DrugRepository repository;

    public List<Drug> findAllByMainCaretaker(AppUser appUser){
        return repository.findByCaretaker(appUser);
    }

    public Drug createDrug(DrugForm drugForm, AppUser appUser) {
        if(!repository.findByNameIgnoreCaseAndCaretaker(drugForm.getName(), appUser).isEmpty()){
            throw new ExistingEntityException("Esta medicação já foi registrada");
        }
        Drug drug = DrugMapper.fromForm(drugForm, appUser);
        return repository.save(drug);
    }

    public Optional<Drug> findById(Long drugId) {
        return repository.findById(drugId);
    }

    public boolean caretakerManagesDrug(Drug drug, AppUser appUser) {
        return drug.getCaretaker().equals(appUser);
    }
}
