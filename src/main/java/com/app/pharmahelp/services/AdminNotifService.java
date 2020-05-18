package com.app.pharmahelp.services;

import com.app.pharmahelp.entities.AdminNotifications;
import com.app.pharmahelp.entities.User;
import com.app.pharmahelp.repository.AdminNotifRepo;
import com.app.pharmahelp.services.interfaces.IAdminNotifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AdminNotifService implements IAdminNotifService {

    @Autowired
    AdminNotifRepo adminNotifRepository;

    @Autowired
    UserService userService;

    private JavaMailSender javaMailSender;

    @Autowired
    public AdminNotifService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendNotifToAdmin() throws MailException
    {
        List<User> allusers = userService.getAllUsers();

        //send email
        SimpleMailMessage mail = new SimpleMailMessage();
        for(User user: allusers){
            if(user.getRole().equals("admin")){
                mail.setTo(user.getEmail());
            }
        }
        mail.setSubject("New validation request");
        mail.setText("A new drug was added. Go to your account page to register the medication");

        javaMailSender.send(mail);
    }

    @Transactional
    public void updateReaded(long id,boolean readed){
        adminNotifRepository.updateReaded(id, readed);
    }

    @Override
    public List<AdminNotifications> getAllAdminNotifications() {
        List<AdminNotifications> result = adminNotifRepository.findAll();
        return result;
    }

    @Override
    public AdminNotifications saveAdminNotification(AdminNotifications adminNotification) {
        AdminNotifications saved = this.adminNotifRepository.save(adminNotification);
        return saved;
    }

    @Override
    @Transactional
    public AdminNotifications updateAdminNotification(Long id, AdminNotifications adminNotification) {
        Optional<AdminNotifications> optional = adminNotifRepository.findById(id);
        AdminNotifications result = optional.orElse(adminNotification);
        return result;
    }

    @Override
    public void deleteDrug(Long id) {
        adminNotifRepository.deleteById(id);

    }
}
