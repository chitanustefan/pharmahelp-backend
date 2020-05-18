package com.app.pharmahelp.services.interfaces;

import com.app.pharmahelp.entities.DrugPerOrder;
import com.app.pharmahelp.entities.Order;

import java.util.List;

public interface IDrugPerOrderService {

    List<DrugPerOrder> getAllDrugsPerOrder();
    DrugPerOrder saveDrugPerOrder(DrugPerOrder drugPerOrder);
    DrugPerOrder updateDrugPerOrder(Long id, DrugPerOrder drugPerOrder);
    void deleteDrugPerOrder(Long id);

}
