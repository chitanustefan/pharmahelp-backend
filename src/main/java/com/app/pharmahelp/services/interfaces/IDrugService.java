package com.app.pharmahelp.services.interfaces;

import com.app.pharmahelp.entities.Drug;
import com.app.pharmahelp.entities.Order;

import java.util.List;

public interface IDrugService {
    Drug findById(long id);
    Drug findByName(String name);
    List<Drug> getAllDrugs();
    Drug saveDrug(Drug drug);
    int updateDrug(Drug drug);
    int deleteDrug(Long id);
    Drug findByNameAndDetails(String name,String detail);
}
