package com.app.pharmahelp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "pharmacist")
public class Pharmacist {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id_pharmacist;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Pharmacy pharmacy;

    public Long getId_pharmacist() {
        return id_pharmacist;
    }

    public void setId_pharmacist(Long id_pharmacist) {
        this.id_pharmacist = id_pharmacist;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
