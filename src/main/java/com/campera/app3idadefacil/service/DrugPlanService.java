package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.model.*;
import com.campera.app3idadefacil.model.datatransfer.form.DrugPlanForm;
import com.campera.app3idadefacil.model.datatransfer.mapper.DrugPlanMapper;
import com.campera.app3idadefacil.repository.DrugPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DrugPlanService {
    @Autowired
    private DrugPlanRepository repository;
    @Autowired
    private  DrugService drugService;
    @Autowired
    private PatientService patientService;
    @Transactional
    public DrugPlan createDrugPlan(DrugPlanForm planForm, AppUser appUser) {
        Drug drug = drugService.findById(planForm.getDrugId(), appUser);
        Patient patient = patientService.findById(planForm.getPatientId(), appUser);
        guardClausesCreateDrugPlan(planForm, appUser, drug, patient);
        DrugPlan plan = DrugPlanMapper.fromForm(planForm, patient, drug);
        return repository.save(plan);
    }

    private void guardClausesCreateDrugPlan(DrugPlanForm planForm, AppUser appUser, Drug drug, Patient patient) {
        if(drug == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento não encontrado.");
        } else if (patient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado.");
        } else if (!patientService.caretakerManagesPatient(patient, appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Paciente não é gerenciado pelo cuidador.");
        } else if (!drugService.caretakerManagesDrug(drug, appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Medicamento não é gerenciado pelo cuidador.");
        } else if (planForm.getPosologyType().equals(PosologyType.CUSTOM)
                && (planForm.getCustomPosologies() == null || planForm.getCustomPosologies().isEmpty())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Posologia customizada não propriamente fornecida.");
        } else if (planForm.getPosologyType().equals(PosologyType.WEEKLY)
                && (planForm.getWeeklyPosology() == null
                || planForm.getWeeklyPosology().getWeeklyPosologyDateTimes() == null
                || planForm.getWeeklyPosology().getWeeklyPosologyDateTimes().isEmpty())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Posologia semanal não propriamente fornecida.");
        } else if (planForm.getPosologyType().equals(PosologyType.UNIFORM) && planForm.getUniformPosology() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Posologia uniforme não propriamente fornecida.");
        }
    }
    public List<DrugPlan> findAllByAdmin(AppUser appUser) {
        return repository.findAllByPatient_Admin(appUser);
    }

    public DrugPlan deleteDrugPlan(Long id, AppUser appUser) {
        Optional<DrugPlan> planOpt = repository.findById(id);
        if (planOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano de medicamento não encontrado.");
        } else if (!patientService.caretakerManagesPatient(planOpt.get().getPatient(), appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Paciente não é gerenciado pelo cuidador.");
        } else if (!drugService.caretakerManagesDrug(planOpt.get().getDrug(), appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Medicamento não é gerenciado pelo cuidador.");
        }
        repository.delete(planOpt.get());
        return planOpt.get();
    }

    public DrugPlan findById(Long id, AppUser appUser) {
        Optional<DrugPlan> planOpt = repository.findById(id);
        if (planOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano de medicamento não encontrado.");
        } else if (!patientService.caretakerManagesPatient(planOpt.get().getPatient(), appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Paciente não é gerenciado pelo cuidador.");
        } else if (!drugService.caretakerManagesDrug(planOpt.get().getDrug(), appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Medicamento não é gerenciado pelo cuidador.");
        }
        return planOpt.get();
    }
}
