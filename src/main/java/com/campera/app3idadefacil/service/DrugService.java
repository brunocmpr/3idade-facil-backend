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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public Drug createDrug(DrugForm drugForm, AppUser appUser, List<MultipartFile> multipartFiles) {
        guardClausesCreateDrug(drugForm, multipartFiles, appUser);
        Drug drug = DrugMapper.fromForm(drugForm, appUser);
        List<UUID> uuids = imageService.saveFilesCreateUuids(multipartFiles);
        List<Image> images = uuids.stream().map(UUID::toString).map(Image::new).collect(Collectors.toList());
        drug.setImages(images);
        Drug savedDrug = repository.save(drug);
        images.forEach(image -> image.setDrug(savedDrug));
        List<Image> savedImages = imageService.saveAll(images);

        savedDrug.setImages(savedImages);
        return savedDrug;
    }

    private void guardClausesCreateDrug(DrugForm drugForm, List<MultipartFile> files, AppUser appUser) {
        if(!repository.findByNameIgnoreCaseAndStrengthIgnoreCaseAndCaretaker(drugForm.getName(), drugForm.getStrength()
                , appUser).isEmpty()){
            throw new ExistingEntityException("Esta medicação já foi registrada");
        }

        imageService.guardClausesCreateImages(files);
    }

    public Optional<Drug> findById(Long drugId) {
        return repository.findById(drugId);
    }

    public boolean caretakerManagesDrug(Drug drug, AppUser appUser) {
        return drug.getCaretaker().equals(appUser);
    }
}
