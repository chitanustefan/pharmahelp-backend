package com.app.pharmahelp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pharmacy")
public class Pharmacy {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_pharmacy",unique = true, nullable = false)
    private Long id_pharmacy;

    @Column
    private String name;

    @Column
    private String location;

    @OneToMany(mappedBy = "pharmacy", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Order> orderList;

    @OneToMany(mappedBy = "pharmacy",  orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column
    @JsonIgnore
    private List<PharmacyStock> pharmacyStockList;

    @OneToMany(mappedBy = "pharmacy", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column
    @JsonIgnore
    private List<Pharmacist> pharmacistsList;

    public Long getId_pharmacy() {
        return id_pharmacy;
    }

    public void setId_pharmacy(Long id_pharmacies) {
        this.id_pharmacy = id_pharmacies;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<PharmacyStock> getPharmacyStockList() {
        return pharmacyStockList;
    }

    public void setPharmacyStockList(List<PharmacyStock> pharmacyStockList) {
        this.pharmacyStockList = pharmacyStockList;
    }

    public List<Pharmacist> getPharmacistsList() {
        return pharmacistsList;
    }

    public void setPharmacistsList(List<Pharmacist> pharmacistsList) {
        this.pharmacistsList = pharmacistsList;
    }

    public void setName(String name) {
        this.name = name;
    }
}
