package com.app.pharmahelp.services;

import com.app.pharmahelp.entities.Pharmacy;
import com.app.pharmahelp.repository.PharmaciesRepo;
import com.app.pharmahelp.services.interfaces.IPharmaciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PharmaciesService implements IPharmaciesService {

    @Autowired
    PharmaciesRepo pharmaciesRepository;

    @Override
    public List<Pharmacy> getAllPharmacies() {
        List<Pharmacy> result = pharmaciesRepository.findAll();
        return result;
    }

    @Override
    public Pharmacy saveOPharmacy(Pharmacy pharmacy) {
        Pharmacy savedPharma = this.pharmaciesRepository.save(pharmacy);
        return savedPharma;
    }

    @Override
    @Transactional
    public int updatePharmacy(Pharmacy pharmacy) {
        return pharmaciesRepository.update(pharmacy.getLocation(),pharmacy.getName(),pharmacy.getId_pharmacy());
    }

    @Override
    public int deletePharmacy(Long id) {
        return pharmaciesRepository.deletePharmacyById(id);
    }

    @Override
    public List<Pharmacy> getPharmaciesByLocation(String location){
        return pharmaciesRepository.findByLocation(location);
    }
}
