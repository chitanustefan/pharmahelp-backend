package com.app.pharmahelp.services;

import com.app.pharmahelp.entities.Drug;
import com.app.pharmahelp.entities.DrugPerOrder;
import com.app.pharmahelp.repository.DrugPerOrderRepo;
import com.app.pharmahelp.services.interfaces.IDrugPerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DrugPerOrderService implements IDrugPerOrderService {


    @Autowired
    DrugPerOrderRepo drugPerOrderRepository;

    @Override
    public List<DrugPerOrder> getAllDrugsPerOrder() {
        List<DrugPerOrder> result = drugPerOrderRepository.findAll();
        return result;
    }

    @Override
    public DrugPerOrder saveDrugPerOrder(DrugPerOrder drugPerOrder) {
        DrugPerOrder saved = this.drugPerOrderRepository.save(drugPerOrder);
        return saved;
    }

    @Override
    @Transactional
    public DrugPerOrder updateDrugPerOrder(Long id, DrugPerOrder drugPerOrder) {
        Optional<DrugPerOrder> optional = drugPerOrderRepository.findById(id);
        DrugPerOrder result = optional.orElse(drugPerOrder);
        return result;
    }

    @Override
    public void deleteDrugPerOrder(Long id) {
        drugPerOrderRepository.deleteById(id);

    }
}
