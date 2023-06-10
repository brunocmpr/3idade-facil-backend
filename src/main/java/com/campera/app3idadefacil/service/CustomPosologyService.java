package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.CustomPosology;
import com.campera.app3idadefacil.model.DrugPlan;
import com.campera.app3idadefacil.model.PosologyType;
import com.campera.app3idadefacil.model.datatransfer.form.CustomPosologyForm;
import com.campera.app3idadefacil.repository.CustomPosologyRepository;
import com.campera.app3idadefacil.repository.DrugPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomPosologyService {
    @Autowired
    private CustomPosologyRepository repository;
    @Autowired
    private DrugPlanRepository drugPlanRepository;

    public List<CustomPosology> saveAll(List<CustomPosology> posologies){
        return repository.saveAll(posologies);
    }

    public DrugPlan replaceCustomPosologies(Long drugPlanId, List<CustomPosologyForm> customPosologiesForm
            , AppUser appUser) {
        DrugPlan plan = drugPlanRepository.findById(drugPlanId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tratamento não encontrado")
        );
        if(!plan.getDrug().getCaretaker().equals(appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não tem permissão para acessar este recurso");
        }else if(!plan.getPatient().getAdmin().equals(appUser)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não tem permissão para acessar este recurso");
        }else if(!plan.getPosologyType().equals(PosologyType.CUSTOM)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tratamento não possui posologia customizada");
        }
        plan.getCustomPosologies().clear();
        DrugPlan clearedPlan = drugPlanRepository.save(plan);
        clearedPlan.addCustomPosologies(customPosologiesForm.stream().map(CustomPosology::new).collect(Collectors.toList()));
        clearedPlan.getCustomPosologies().forEach(posology -> posology.setDrugPlan(clearedPlan));
        repository.saveAll(clearedPlan.getCustomPosologies());
        return drugPlanRepository.save(clearedPlan);
    }
}
