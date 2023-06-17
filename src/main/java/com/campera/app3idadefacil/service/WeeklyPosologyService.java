package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.DrugPlan;
import com.campera.app3idadefacil.model.PosologyType;
import com.campera.app3idadefacil.model.WeeklyPosologyDateTime;
import com.campera.app3idadefacil.model.datatransfer.form.UniformPosologyForm;
import com.campera.app3idadefacil.model.datatransfer.form.WeeklyPosologyForm;
import com.campera.app3idadefacil.repository.DrugPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WeeklyPosologyService {
    @Autowired
    private DrugPlanRepository drugPlanRepository;
    public DrugPlan update(Long drugPlanId, WeeklyPosologyForm form, AppUser appUser) {
        DrugPlan plan = drugPlanRepository.findById(drugPlanId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tratamento não encontrado")
        );
        if(!plan.getDrug().getCaretaker().equals(appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não tem permissão para acessar este recurso");
        }else if(!plan.getPatient().getAdmin().equals(appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não tem permissão para acessar este recurso");
        }else if(!plan.getPosologyType().equals(PosologyType.WEEKLY)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tratamento não possui posologia semanal");
        }

        plan.getWeeklyPosology().setStartDateTime(form.getStartDateTime());
        plan.getWeeklyPosology().setEndDateTime(form.getEndDateTime());
        plan.getWeeklyPosology().getWeeklyPosologyDateTimes().clear();
        List<WeeklyPosologyDateTime> newWeeklyDateTimes
                = form.getWeeklyPosologyDateTimes().stream().map(WeeklyPosologyDateTime::new).toList();
        newWeeklyDateTimes.stream().forEach(date -> date.setWeeklyPosology(plan.getWeeklyPosology()));
        plan.getWeeklyPosology().getWeeklyPosologyDateTimes().addAll(newWeeklyDateTimes);

        return drugPlanRepository.save(plan);
    }
}
