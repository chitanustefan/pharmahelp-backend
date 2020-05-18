package com.app.pharmahelp.services.interfaces;

import com.app.pharmahelp.entities.AdminNotifications;
import com.app.pharmahelp.entities.Drug;

import java.util.List;

public interface IAdminNotifService {

    List<AdminNotifications> getAllAdminNotifications();
    AdminNotifications saveAdminNotification(AdminNotifications adminNotification);
    AdminNotifications updateAdminNotification(Long id, AdminNotifications adminNotification);
    void deleteDrug(Long id);
}
