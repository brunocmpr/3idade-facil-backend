package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Image;
import com.campera.app3idadefacil.model.Patient;
import com.campera.app3idadefacil.model.datatransfer.form.PatientForm;
import com.campera.app3idadefacil.model.datatransfer.mapper.PatientMapper;
import com.campera.app3idadefacil.repository.DrugPlanRepository;
import com.campera.app3idadefacil.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private DrugPlanRepository drugPlanRepository;

    public Patient create(PatientForm form, AppUser appUser, Optional<List<MultipartFile>> multipartFiles) {
        Patient patient = PatientMapper.convertFromForm(form, appUser);

        List<Image> images = new ArrayList<>();
        if(multipartFiles.isPresent() && !multipartFiles.get().isEmpty()){
            images = imageService.persistFilesAndGenerateNonPersistedImages(multipartFiles.get()
                    , imageService.patientStorageDir);
        }
        patient.setImages(images);

        Patient savedPatient = repository.save(patient);

        images.forEach(image -> image.setPatient(savedPatient));
        List<Image> savedImages = imageService.saveAll(images);

        savedPatient.setImages(savedImages);
        return savedPatient;
    }

    public List<Patient> findAllByAdmin(AppUser appUser) {
        List<Patient> patients = repository.findAllByAdmin(appUser);
        return patients;
    }

    public Optional<Patient> findById(Long patientId) {
        return repository.findById(patientId);
    }

    public boolean caretakerManagesPatient(Patient patient, AppUser appUser) {
        return patient.getAdmin().equals(appUser);
    }

    public Patient delete(Long id, AppUser user) {
        Optional<Patient> patient = repository.findById(id);
        guardClausesDeletePatient(user, patient);
        if(patient.get().getDrugPlans()!= null && !patient.get().getDrugPlans().isEmpty()){
            drugPlanRepository.deleteAll(patient.get().getDrugPlans());
        }
        if(patient.get().getImages() != null && !patient.get().getImages().isEmpty()){
            imageService.deleteAll(patient.get().getImages());
        }
        repository.delete(patient.get());
        return patient.get();
    }

    private void guardClausesDeletePatient(AppUser user, Optional<Patient> patient) {
        if(patient.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado");
        }
        if(!patient.get().getAdmin().equals(user)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para deletar este paciente");
        }
    }
}
