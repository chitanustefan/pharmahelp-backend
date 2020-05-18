package com.app.pharmahelp.services.interfaces;

import com.app.pharmahelp.entities.PharmacyStock;

import java.util.List;

public interface IPharmacyStock {

    List<PharmacyStock> getAllPharmaciyStocks();
    PharmacyStock savePharmacyStock(PharmacyStock pharmacyStock);
    PharmacyStock updatePharmacyStock(Long id, PharmacyStock pharmacyStock);
    void deletePharmacyStock(Long id);

}
