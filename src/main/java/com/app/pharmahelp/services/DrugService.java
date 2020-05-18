package com.app.pharmahelp.services;

import com.app.pharmahelp.entities.Drug;
import com.app.pharmahelp.entities.Order;
import com.app.pharmahelp.repository.DrugRepo;
import com.app.pharmahelp.services.interfaces.IDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DrugService implements IDrugService {


    @Autowired
    DrugRepo drugRepository;

    @Override
    public List<Drug> getAllDrugs() {
        List<Drug> result = drugRepository.findAll();
        return result;
    }

    @Override
    public Drug saveDrug(Drug drug) {
        Drug saved = this.drugRepository.save(drug);
        return saved;
    }

    @Transactional
    public void validateDrug(long id_drug,String status){
        drugRepository.validateDrug(id_drug,status);
    }

    @Override
    @Transactional
    public int updateDrug(Drug drug) {
        return drugRepository.update(drug.getDetails(),drug.getName(),drug.getProspectus(), drug.getActiveSubstance(),drug.getId_drug());

    }

    @Override
    public int deleteDrug(Long id) {
        return drugRepository.deleteDrugById(id);
    }

    @Override
    public Drug findById(long Id){
       return drugRepository.findById(Id);
    }
    @Override
    public Drug findByName(String name){
        return drugRepository.findByName(name);
    }
    @Override
    public Drug findByNameAndDetails(String name,String detail){
        return  drugRepository.findByNameAndDetails(name,detail);
    }

    public List<Drug> getDrugsSameActiveSubstance(String activeSubstance){
        List<Drug> all = drugRepository.findAll();
        List<Drug> drugs = new ArrayList<>();
        for(Drug d: all){
            if(d.getActiveSubstance().equals(activeSubstance) && d.getStatus().equals("approved")){
                drugs.add(d);
            }
        }
        return drugs;
    }
}
