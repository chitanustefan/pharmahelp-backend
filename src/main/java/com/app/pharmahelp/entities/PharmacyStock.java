package com.app.pharmahelp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "pharmacy_stock")
public class PharmacyStock {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_pharmacy_stock",unique = true, nullable = false)
    private Long id_pharmacy_stock;

    @Column
    private Long price;

    @Column
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private Drug drug;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Pharmacy pharmacy;

    public Long getId_pharmacy_stock() {
        return id_pharmacy_stock;
    }

    public void setId_pharmacy_stock(Long id_pharmacy_stock) {
        this.id_pharmacy_stock = id_pharmacy_stock;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
