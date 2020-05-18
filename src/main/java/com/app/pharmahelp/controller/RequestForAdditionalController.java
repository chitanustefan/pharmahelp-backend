package com.app.pharmahelp.controller;

import com.app.pharmahelp.entities.Drug;
import com.app.pharmahelp.entities.RequestForAdditional;
import com.app.pharmahelp.entities.User;
import com.app.pharmahelp.security.payloads.ApiResponse;
import com.app.pharmahelp.services.AdminNotifService;
import com.app.pharmahelp.services.DrugService;
import com.app.pharmahelp.services.PharmacyStockService;
import com.app.pharmahelp.services.RequestForAdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/rfa")
public class RequestForAdditionalController {
    @Autowired
    RequestForAdditionalService requestForAdditionalService;
    @Autowired
    DrugService drugService;
    @Autowired
    PharmacyStockService pharmacyStockService;
    @Autowired
    AdminNotifService adminNotifService;

    @PostMapping("/requestDrug")
    public ResponseEntity<?> requestDrug(@RequestParam String name,@RequestParam String location,@RequestParam  Long idUser) {

        RequestForAdditional requestForAdditional=requestForAdditionalService.requestDrug(name,location,idUser);
       if(requestForAdditional==null)
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error!Drug does not exist or name is incorrect");
       else
           return  ResponseEntity.ok(new ApiResponse(true,"Request registered sucessfully!"));

    }

    @GetMapping("name")
    public Long getDrugByName(@RequestBody String name) {
        return drugService.findByName(name).getId_drug();
    }

    @GetMapping("/mostRequestedDrug")
    public Drug mostRequestedDrug(@RequestParam String location) {

            List<RequestForAdditional> list = requestForAdditionalService.mostReqeustDrug(location);
            System.out.println(list.get(0).getDrug().getId_drug());
            return  list.get(0).getDrug();

    }

}
