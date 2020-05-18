package com.app.pharmahelp.controller;


import com.app.pharmahelp.entities.Drug;
import com.app.pharmahelp.entities.Pharmacy;
import com.app.pharmahelp.entities.PharmacyStock;
import com.app.pharmahelp.entities.RequestForAdditional;
import com.app.pharmahelp.security.payloads.ApiResponse;
import com.app.pharmahelp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/pharmacystock")
public class PharmacyStockController {

    @Autowired
    PharmacyStockService pharmacyStockService;
    @Autowired
    PharmaciesService pharmaciesService;
    @Autowired
    DrugService drugService;
    @Autowired
    RequestForAdditionalService requestForAdditionalService;


    @PostMapping("/updateQuantity")
    public ResponseEntity<?> updateQuantity(@RequestParam long id, @RequestParam int quantity)
    {
        pharmacyStockService.updateQuantity(id,quantity);
        return ResponseEntity.ok(new ApiResponse(true,"Drug's quantity successfully updated" ));
    }

    @GetMapping("/getallbypharmacist")
    public List<PharmacyStock> getPharmacyStocksByPharmacist(@RequestParam long idUser){
        return pharmacyStockService.getAllPharmacyStocksByPharmacist(idUser);
    }
    @GetMapping("/getallbyidpharmacy")
    public List<PharmacyStock> getPharmacyStocksByIdPharmacy(@RequestParam long idPharmacy){
        return pharmacyStockService.getAllPharmacyStocksByIdPharmacy(idPharmacy);
    }

    @GetMapping("/mostRequestedDrug")
    public List<Drug> getMostRequestedDrugs(@RequestParam String location){
        List<Drug> drugs=new ArrayList<>();
        /*
        List<Pharmacy> pharmacies=pharmaciesService.getPharmaciesByLocation(location);
        for(Pharmacy pharmacy:pharmacies){
            List<PharmacyStock> pharmacyStocklist=pharmacyStockService.getAllPharmacyStocksByIdPharmacy(pharmacy.getId_pharmacy());
            for(PharmacyStock pharmacyStock:pharmacyStocklist){
                if(pharmacyStock.getQuantity()==0){
                    drugs.add(drugService.findById(pharmacyStock.getId_pharmacy_stock()));
                }
            }
        }
*/
        List<RequestForAdditional> requestForAdditionals=requestForAdditionalService.getAllByLocation(location);
        for(RequestForAdditional request:requestForAdditionals){
            drugs.add(drugService.findById(request.getDrug().getId_drug()));

        }
        return drugs;
    }

    @GetMapping("/locations")
    public List<String> getAllPharmacyLocations(){
       List<Pharmacy> pharmacies=pharmaciesService.getAllPharmacies();
        List<String> result=new ArrayList<>();
        for(Pharmacy phar:pharmacies){
            String s =phar.getLocation();
            String[] tokens=s.split(",");
                String t = tokens[0];
                result.add(t);

        }
        return result;
    }

    @PostMapping("/insertInStock")
    public ResponseEntity<?> insertInStock(@RequestBody PharmacyStock pharmacyStock, @RequestParam long idUser, @RequestParam long idDrug)
    {
        PharmacyStock newPS = pharmacyStockService.insertInStock(pharmacyStock,idUser,idDrug);
        if(newPS==null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error!");
        else
            return ResponseEntity.ok(new ApiResponse(true,"Drug's quantity successfully inserted" ));
    }


}
