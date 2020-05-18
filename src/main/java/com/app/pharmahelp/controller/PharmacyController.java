package com.app.pharmahelp.controller;

import com.app.pharmahelp.entities.Pharmacy;
import com.app.pharmahelp.security.payloads.ApiResponse;
import com.app.pharmahelp.services.PharmaciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {
    @Autowired
    private PharmaciesService pharmacyService;

    @PostMapping("/register")
    public ResponseEntity<?> registerPharmacy(@RequestParam String name, @RequestParam String location)
    {
        //unique location
        List<Pharmacy> allPharmacies = pharmacyService.getAllPharmacies();
        for (Pharmacy p: allPharmacies){
            if(p.getLocation().equals(location)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Pharmacy could not be registered. Location already exists");
            }
        }

        Pharmacy newpharmacy = new Pharmacy();
        newpharmacy.setName(name);
        newpharmacy.setLocation(location);
        pharmacyService.saveOPharmacy(newpharmacy);

        return ResponseEntity.ok(new ApiResponse(true,"Pharmacy successfully registered" ));
    }

    @GetMapping("/all")
    public List<Pharmacy> getAllPharmacies(){
        return pharmacyService.getAllPharmacies();
    }

    @PostMapping("/update")
    public ResponseEntity<?> updatePharmacy(@RequestBody Pharmacy pharmacy){
        int result= pharmacyService.updatePharmacy(pharmacy);
        if(result==1)
            return ResponseEntity.ok(new ApiResponse(true,"Pharmacy successfully updated!"));
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Pharmacy could not be updated or doesn't exist!");
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deletePharmacy(@RequestBody Pharmacy pharmacy){
        int result= pharmacyService.deletePharmacy(pharmacy.getId_pharmacy());
        if(result==1)
            return ResponseEntity.ok(new ApiResponse(true,"Pharmacy successfully deleted!"));
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Pharmacy could not be deleted or doesn't exist!");
    }
}
