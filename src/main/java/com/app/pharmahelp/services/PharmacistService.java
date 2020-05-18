package com.app.pharmahelp.services;

import com.app.pharmahelp.entities.Pharmacist;
import com.app.pharmahelp.entities.Pharmacy;
import com.app.pharmahelp.repository.PharmacistRepo;
import com.app.pharmahelp.services.interfaces.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacistService implements IPharmacistService {


    @Autowired
    private PharmacistRepo pharmacistRepository;

    @Override
    public List<Pharmacist> getAllPharmacist() {
        List<Pharmacist> result = pharmacistRepository.findAll();
        return result;
    }

    @Override
    public Pharmacist saveOPharmacist(Pharmacist pharmacist) {
        Pharmacist savedPharma = this.pharmacistRepository.save(pharmacist);
        return savedPharma;
    }

    @Override
    public Pharmacist updatePharmacist(Long id, Pharmacist pharmacist) {
        Optional<Pharmacist> optionalPharmacies = pharmacistRepository.findById(id);
        Pharmacist result = optionalPharmacies.orElse(pharmacist);
        return result;
    }

    @Override
    public void deletePharmacist(Long id) {
            pharmacistRepository.deleteById(id);
    }


    public Pharmacy getPharmacyByPharmacist(Long id_user){
        return pharmacistRepository.getPharmacyByPharmacist(id_user);
    }
}
