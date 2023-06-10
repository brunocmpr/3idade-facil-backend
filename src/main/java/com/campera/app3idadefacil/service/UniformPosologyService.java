package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.DrugPlan;
import com.campera.app3idadefacil.model.PosologyType;
import com.campera.app3idadefacil.model.datatransfer.form.UniformPosologyForm;
import com.campera.app3idadefacil.repository.DrugPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UniformPosologyService {

    @Autowired
    private DrugPlanRepository drugPlanRepository;
    public DrugPlan update(Long drugPlanId, UniformPosologyForm form, AppUser appUser) {
        DrugPlan plan = drugPlanRepository.findById(drugPlanId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tratamento não encontrado")
        );
        if(!plan.getDrug().getCaretaker().equals(appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não tem permissão para acessar este recurso");
        }else if(!plan.getPatient().getAdmin().equals(appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não tem permissão para acessar este recurso");
        }else if(!plan.getPosologyType().equals(PosologyType.UNIFORM)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tratamento não possui posologia uniforme");
        }

        plan.getUniformPosology().setStartDateTime(form.getStartDateTime());
        plan.getUniformPosology().setEndDateTime(form.getEndDateTime());
        plan.getUniformPosology().setTimeLength(form.getTimeLength());
        plan.getUniformPosology().setTimeUnit(form.getTimeUnit());

        return drugPlanRepository.save(plan);
    }
}
