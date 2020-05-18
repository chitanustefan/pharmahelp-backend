package com.app.pharmahelp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "admin_notifications")
public class AdminNotifications {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_admin_notifications",unique = true, nullable = false)
    private Long id_admin_notifications;

    @Column
    private String description;

    @Column
    private Date createdDate;

    @Column
    private boolean readed;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Long getId_admin_notifications() {
        return id_admin_notifications;
    }

    public void setId_admin_notifications(Long id_admin_notifications) {
        this.id_admin_notifications = id_admin_notifications;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
