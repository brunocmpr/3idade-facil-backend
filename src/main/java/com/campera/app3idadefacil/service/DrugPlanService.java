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
        Optional<Drug> drugOpt = drugService.findById(planForm.getDrugId());
        Optional<Patient> patientOpt = patientService.findById(planForm.getPatientId());
        guardClausesCreateDrugPlan(planForm, appUser, drugOpt, patientOpt);
        DrugPlan plan = DrugPlanMapper.fromForm(planForm, patientOpt.get(), drugOpt.get());
        return repository.save(plan);
    }

    private void guardClausesCreateDrugPlan(DrugPlanForm planForm, AppUser appUser, Optional<Drug> drugOpt
            , Optional<Patient> patientOpt) {
        if(drugOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento não encontrado.");
        } else if (patientOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado.");
        } else if (!patientService.caretakerManagesPatient(patientOpt.get(), appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Paciente não é gerenciado pelo cuidador.");
        } else if (!drugService.caretakerManagesDrug(drugOpt.get(), appUser)){
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
}
