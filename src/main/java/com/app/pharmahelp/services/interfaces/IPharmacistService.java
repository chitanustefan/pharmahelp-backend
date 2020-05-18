package com.app.pharmahelp.services.interfaces;

import com.app.pharmahelp.entities.Pharmacist;

import java.util.List;

public interface IPharmacistService {

    List<Pharmacist> getAllPharmacist();
    Pharmacist saveOPharmacist(Pharmacist pharmacist);
    Pharmacist updatePharmacist(Long id, Pharmacist pharmacist);
    void deletePharmacist(Long id);
}
