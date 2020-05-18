package com.app.pharmahelp.services;


import com.app.pharmahelp.entities.Drug;
import com.app.pharmahelp.entities.Pharmacy;
import com.app.pharmahelp.entities.PharmacyStock;
import com.app.pharmahelp.repository.PharmacyStockRepo;
import com.app.pharmahelp.services.interfaces.IPharmacyStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PharmacyStockService implements IPharmacyStock {

    @Autowired
    PharmacyStockRepo pharmacyStockRepository;
    @Autowired
    PharmacistService pharmacistService;
    @Autowired
    DrugService drugService;

    @Override
    public List<PharmacyStock> getAllPharmaciyStocks() {
        List<PharmacyStock> result = pharmacyStockRepository.findAll();
        return result;
    }

    @Override
    public PharmacyStock savePharmacyStock(PharmacyStock pharmacyStock) {
        PharmacyStock savedPharma = this.pharmacyStockRepository.save(pharmacyStock);
        return savedPharma;
    }

    @Override
    @Transactional
    public PharmacyStock updatePharmacyStock(Long id, PharmacyStock pharmacyStock) {
        Optional<PharmacyStock> optionalPharmacies = pharmacyStockRepository.findById(id);
        PharmacyStock result = optionalPharmacies.orElse(pharmacyStock);
        return result;
    }

    @Override
    public void deletePharmacyStock(Long id) {
        pharmacyStockRepository.deleteById(id);

    }

    @Transactional
    public void updateQuantity(long id_pharmacy_stock, int quant){
        pharmacyStockRepository.updateQuantity(id_pharmacy_stock,quant);
    }

    @Transactional
    public void updateQuantityAndPrice(long id_pharmacy_stock, int quant, Long price){
        pharmacyStockRepository.updateQuantityAndPrice(id_pharmacy_stock,quant,price);
    }


    public List<PharmacyStock> getAllPharmacyStocksByPharmacist(long idUser) {
        Pharmacy pharmacy=pharmacistService.getPharmacyByPharmacist(idUser);
        return pharmacyStockRepository.getPharmacyStocksByIdPharmacy(pharmacy.getId_pharmacy());
    }

    public List<PharmacyStock> getAllPharmacyStocksByIdPharmacy(long idPharmacy) {
        return pharmacyStockRepository.getPharmacyStocksByIdPharmacy(idPharmacy);
    }


    public PharmacyStock insertInStock(PharmacyStock pharmacyStock,Long id_user, Long id_drug){
        Drug drug=drugService.findById(id_drug);
        Pharmacy pharmacy=pharmacistService.getPharmacyByPharmacist(id_user);
        if(pharmacy==null)
            return null;
        else {
            if(drug==null)
                return null;
            else {
                PharmacyStock ps= pharmacyStockRepository.getPharmacyStockByDrugAndPharmacy(id_drug,pharmacy.getId_pharmacy());
                if(ps==null) {
                    pharmacyStock.setPharmacy(pharmacy);
                    pharmacyStock.setDrug(drug);
                    return this.savePharmacyStock(pharmacyStock);
                }
                else{
                    int quantity=ps.getQuantity()+pharmacyStock.getQuantity();
                    this.updateQuantityAndPrice(ps.getId_pharmacy_stock(),quantity,pharmacyStock.getPrice());
                    return ps;
                }
            }
        }
    }
}
