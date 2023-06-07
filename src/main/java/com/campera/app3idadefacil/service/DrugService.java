package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.exception.ExistingEntityException;
import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.Image;
import com.campera.app3idadefacil.model.Patient;
import com.campera.app3idadefacil.model.datatransfer.mapper.DrugMapper;
import com.campera.app3idadefacil.model.datatransfer.form.DrugForm;
import com.campera.app3idadefacil.model.datatransfer.mapper.PatientMapper;
import com.campera.app3idadefacil.repository.DrugPlanRepository;
import com.campera.app3idadefacil.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DrugService {
    @Autowired
    private DrugRepository repository;

    @Autowired
    private ImageService imageService;
    @Autowired
    private DrugPlanRepository drugPlanRepository;

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

    public Drug findById(Long drugId, AppUser appUser) {
        Optional<Drug> drugOpt = repository.findById(drugId);
        guardClausesFindById(drugOpt, appUser);
        return drugOpt.get();
    }

    private void guardClausesFindById(Optional<Drug> patientOpt, AppUser principal) {
        if(patientOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento não encontrado");
        }
        if(!patientOpt.get().getCaretaker().equals(principal)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN
                    , "Usuário não tem permissão para visualizar este medicamento");
        }
    }

    public boolean caretakerManagesDrug(Drug drug, AppUser appUser) {
        return drug.getCaretaker().equals(appUser);
    }

    public Drug deleteDrug(Long id, AppUser user) {
        Optional<Drug> drugOpt = repository.findById(id);
        guardClausesDeleteDrug(user, drugOpt);
        if(drugOpt.get().getDrugPlans()!= null && !drugOpt.get().getDrugPlans().isEmpty()){
            drugPlanRepository.deleteAll(drugOpt.get().getDrugPlans());
        }
        if(drugOpt.get().getImages() != null && !drugOpt.get().getImages().isEmpty()){
            imageService.deleteAll(drugOpt.get().getImages());
        }
        repository.delete(drugOpt.get());
        return drugOpt.get();
    }

    private void guardClausesDeleteDrug(AppUser user, Optional<Drug> drugOpt) {
        if(drugOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento não encontrado");
        }
        if(!drugOpt.get().getCaretaker().equals(user)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN
                    , "Usuário não tem permissão para deletar este medicamento");
        }
    }

    public Drug updateDrug(Long id, DrugForm form, AppUser appUser, Optional<List<MultipartFile>> multipartFiles) {
        Optional<Drug> drugOpt = repository.findById(id);
        guardClausesUpdateDrug(drugOpt, appUser);

        Drug drug = DrugMapper.updateFromForm(drugOpt.get(), form);

        imageService.deleteAll(drug.getImages());
        List<Image> images = new ArrayList<>();
        if(multipartFiles.isPresent() && !multipartFiles.get().isEmpty()){
            images = imageService.persistFilesAndGenerateNonPersistedImages(multipartFiles.get()
                    , imageService.patientStorageDir);
        }
        images.forEach(image -> image.setDrug(drug));
        List<Image> savedImages = imageService.saveAll(images);

        drug.setImages(savedImages);
        Drug savedDrug = repository.save(drug);
        return savedDrug;
    }

    private void guardClausesUpdateDrug(Optional<Drug> drugOpt, AppUser appUser) {
        if(drugOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento não encontrado");
        }
        if(!drugOpt.get().getCaretaker().equals(appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN
                    , "Usuário não tem permissão para atualizar este medicamento");
        }
    }
}
