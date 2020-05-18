package com.app.pharmahelp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id_user;

    @Column(nullable=true)
    private String fullName;

    @Column(nullable=false, unique=true)
    @Email(message="{errors.invalid_email}")
    private String email;

    @Column(nullable=true)
    @Size(min=4)
    private String password;

    @Column(nullable=true)
    private String role;

    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Pharmacist pharmacist;

//    @OneToOne(mappedBy = "user", cascade=CascadeType.ALL)
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Reviews> review;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<RequestForAdditional> requestForAdditional;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Order> order;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<AdminNotifications> adminNotifications;

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public List<AdminNotifications> getAdminNotifications() {
        return adminNotifications;
    }

    public void setAdminNotifications(List<AdminNotifications> adminNotifications) {
        this.adminNotifications = adminNotifications;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public List<Reviews> getReview() {
        return review;
    }

    public void setReview(List<Reviews> review) {
        this.review = review;
    }

    public List<RequestForAdditional> getRequestForAdditional() {
        return requestForAdditional;
    }

    public void setRequestForAdditional(List<RequestForAdditional> requestForAdditional) {
        this.requestForAdditional = requestForAdditional;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
