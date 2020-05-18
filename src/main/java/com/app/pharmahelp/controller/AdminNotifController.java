package com.app.pharmahelp.controller;

import com.app.pharmahelp.entities.AdminNotifications;
import com.app.pharmahelp.security.payloads.ApiResponse;
import com.app.pharmahelp.services.AdminNotifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class AdminNotifController {
    @Autowired
    private AdminNotifService notifService;

    @GetMapping("/getall")
    public List<AdminNotifications> getAllNotifications()
    {
        return notifService.getAllAdminNotifications();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateNotification(@RequestParam long id, @RequestParam boolean readed)
    {
        notifService.updateReaded(id, readed);
        return ResponseEntity.ok(new ApiResponse(true,"Notification successfully updated" ));
    }

    @GetMapping("/getunread")
    public List<AdminNotifications> getAllUnreadNotif(){
        List<AdminNotifications> all = notifService.getAllAdminNotifications();
        List<AdminNotifications> unread = new ArrayList<>();

        for(AdminNotifications notif : all){
            if(notif.isReaded()==false){
                unread.add(notif);
            }
        }

        return unread;
    }

}
