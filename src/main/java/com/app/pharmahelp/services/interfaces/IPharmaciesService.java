package com.app.pharmahelp.services.interfaces;

import com.app.pharmahelp.entities.Pharmacy;

import java.util.List;

public interface IPharmaciesService {
    List<Pharmacy> getPharmaciesByLocation(String loaction);
    List<Pharmacy> getAllPharmacies();
    Pharmacy saveOPharmacy(Pharmacy pharmacy);
    int updatePharmacy(Pharmacy pharmacy);
    int deletePharmacy(Long id);
}
