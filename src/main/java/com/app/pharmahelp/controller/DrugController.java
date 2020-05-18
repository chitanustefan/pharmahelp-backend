package com.app.pharmahelp.controller;

import com.app.pharmahelp.entities.AdminNotifications;
import com.app.pharmahelp.entities.Drug;
import com.app.pharmahelp.entities.DrugPerOrder;
import com.app.pharmahelp.entities.User;
import com.app.pharmahelp.security.payloads.ApiResponse;
import com.app.pharmahelp.services.AdminNotifService;
import com.app.pharmahelp.services.DrugPerOrderService;
import com.app.pharmahelp.services.DrugService;
import com.app.pharmahelp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/drug")
public class DrugController {

    @Autowired
    DrugService drugService;

    @Autowired
    private AdminNotifService notifService;

    @Autowired
    private UserService userService;

    @Autowired
    private DrugPerOrderService drugPerOrderService;

    @PostMapping("/validate")
    public ResponseEntity<?> validateDrug(@RequestParam long id,@RequestParam String status)
    {
        drugService.validateDrug(id,status);
        return ResponseEntity.ok(new ApiResponse(true,"Drug's status successfully updated" ));
    }

    @GetMapping("/getall")
    public List<Drug> validateDrug()
    {
        return drugService.getAllDrugs();
    }



    //insert drug by pharmacist, create notification, send ack mail to admin
    @PostMapping("/pharmacist/add")
    public ResponseEntity<?> addDrug(@RequestBody Drug drug, @RequestParam long id)
    {
        List<Drug> allDrugs = drugService.getAllDrugs();
        for (Drug d : allDrugs){
            if (d.getName().equalsIgnoreCase(drug.getName())){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Drug already exists");
            }
        }

        Drug newmed = new Drug();
        newmed.setName(drug.getName());
        newmed.setDetails(drug.getDetails());
        newmed.setProspectus(drug.getProspectus());
        newmed.setActiveSubstance(drug.getActiveSubstance());
        newmed.setStatus("pending");
        drugService.saveDrug(newmed);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        List<User> usersList = userService.getAllUsers();

        AdminNotifications newnotif = new AdminNotifications();
        newnotif.setDescription("New drug added");
        newnotif.setCreatedDate(date);
        for(User u : usersList){
            if(u.getId_user()==id){
                newnotif.setUser(u);
            }
        }
        newnotif.setReaded(false);
        notifService.saveAdminNotification(newnotif);

        //send ack mail to admin
        try {
            notifService.sendNotifToAdmin();
        } catch (MailException e) {
            //catch error
        }
        return ResponseEntity.ok(new ApiResponse(true,"Drug added. Waiting to be validated by admin" ));
    }

    //register drug by admin: drug status automatically approved
    @PostMapping("/admin/add")
    public ResponseEntity<?> registerDrugByAdmin(@RequestBody Drug drug){
        List<Drug> allDrugs = drugService.getAllDrugs();
        for (Drug d : allDrugs){
            if (d.getName().equalsIgnoreCase(drug.getName())){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Drug already exists");
            }
        }

        Drug newmed = new Drug();
        newmed.setName(drug.getName());
        newmed.setDetails(drug.getDetails());
        newmed.setProspectus(drug.getProspectus());
        newmed.setActiveSubstance(drug.getActiveSubstance());
        newmed.setStatus("approved");
        drugService.saveDrug(newmed);

        return ResponseEntity.ok(new ApiResponse(true,"Drug successfully registered" ));
    }



    @PostMapping("/update")
    public ResponseEntity<?> updateDrug(@RequestBody Drug drug){
        int result= drugService.updateDrug(drug);
        if(result==1)
            return ResponseEntity.ok(new ApiResponse(true, "Drug successfully updated!"));
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Drug could not be updated or doesn't exist!");
    }

    @PostMapping("/delete")
    public  ResponseEntity<?> deleteDrug(@RequestBody Drug drug) {
        int result = drugService.deleteDrug(drug.getId_drug());
        if(result==1)
            return ResponseEntity.ok(new ApiResponse(true, "Drug successfully deleted!"));
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Drug could not be deleted or doesn't exist!");
    }

    @GetMapping("/get/sameActiveSubst")
    public Map<String, Integer> getDrugsSameActiveSubstance(@RequestParam String activeSubstance){
        List<Drug> drugs = drugService.getDrugsSameActiveSubstance(activeSubstance);

        List<DrugPerOrder> drugPerOrderList = drugPerOrderService.getAllDrugsPerOrder();
        Map<String, Integer> map = new HashMap<>();
        for(Drug d: drugs){
            int count = 0;
            for(DrugPerOrder dpo: drugPerOrderList){
                if(dpo.getDrug().getId_drug() == d.getId_drug()){
                    count += dpo.getQuantity();
                }
            }
            map.put(d.getName(),count);
        }
        return map;
    }

}
