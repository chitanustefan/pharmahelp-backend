package com.app.pharmahelp.repository;

import com.app.pharmahelp.entities.AdminNotifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminNotifRepo extends JpaRepository<AdminNotifications, Long> {
    @Transactional
    @Modifying
    @Query("update AdminNotifications a set a.readed=?2 where a.id_admin_notifications = ?1")
    void updateReaded(long id_admin_notifications, boolean readed);
}
