package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.exception.ExistingEntityException;
import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.Image;
import com.campera.app3idadefacil.model.datatransfer.mapper.DrugMapper;
import com.campera.app3idadefacil.model.datatransfer.form.DrugForm;
import com.campera.app3idadefacil.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DrugService {
    @Autowired
    private DrugRepository repository;

    @Autowired
    private ImageService imageService;

    public List<Drug> findAllByMainCaretaker(AppUser appUser){
        return repository.findByCaretaker(appUser);
    }

    @Transactional
    public Drug createDrug(DrugForm drugForm, AppUser appUser, Optional<List<MultipartFile>> multipartFiles) {
        guardClausesCreateDrug(drugForm, multipartFiles, appUser);
        
        Drug drug = DrugMapper.fromForm(drugForm, appUser);
        List<Image> images = new ArrayList<>();
        if(multipartFiles.isPresent() && !multipartFiles.get().isEmpty()){
            images = imageService.persistFilesAndGenerateNonPersistedImages(multipartFiles.get()
                    , imageService.drugStorageDir);
        }
        drug.setImages(images);

        Drug savedDrug = repository.save(drug);

        images.forEach(image -> image.setDrug(savedDrug));
        List<Image> savedImages = imageService.saveAll(images);

        savedDrug.setImages(savedImages);
        return savedDrug;
    }

    private void guardClausesCreateDrug(DrugForm drugForm, Optional<List<MultipartFile>> files, AppUser appUser) {
        if(!repository.findByNameIgnoreCaseAndStrengthIgnoreCaseAndCaretaker(drugForm.getName(), drugForm.getStrength()
                , appUser).isEmpty()){
            throw new ExistingEntityException("Esta medicação já foi registrada");
        }
        if(files.isPresent() && !files.get().isEmpty()){
            imageService.guardClausesCreateImages(files.get());
        }
    }

    public Optional<Drug> findById(Long drugId) {
        return repository.findById(drugId);
    }

    public boolean caretakerManagesDrug(Drug drug, AppUser appUser) {
        return drug.getCaretaker().equals(appUser);
    }
}
