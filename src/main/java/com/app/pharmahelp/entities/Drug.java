package com.app.pharmahelp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drug")
public class Drug {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_drug",unique = true, nullable = false)
    private Long id_drug;

    @Column
    private String name;

    @Column
    private String status;

    @Column
    private String details;

    @Column
    private String prospectus;

    @Column(name = "active_substance")
    private String activeSubstance;

    @Column(nullable = true)
    @OneToMany(mappedBy = "drug",  orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<DrugPerOrder> drugPerOrders;


    @OneToMany(mappedBy = "drug", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column
    @JsonIgnore
    private List<Reviews> reviewsList;

    @OneToMany(mappedBy = "drug", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column
    @JsonIgnore
    private List<PharmacyStock> pharmacyStockList;

    @OneToMany(mappedBy = "drug", orphanRemoval = true, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column
    @JsonIgnore
    private List<RequestForAdditional> requestForAdditional;

    public Long getId_drug() {
        return id_drug;
    }

    public void setId_drug(Long id_drug) {
        this.id_drug = id_drug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getProspectus() {
        return prospectus;
    }

    public void setProspectus(String prospectus) {
        this.prospectus = prospectus;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public List<DrugPerOrder> getDrugPerOrders() {
        return drugPerOrders;
    }

    public void setDrugPerOrders(List<DrugPerOrder> drugPerOrders) {
        this.drugPerOrders = drugPerOrders;
    }

    public List<Reviews> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }

    public List<PharmacyStock> getPharmacyStockList() {
        return pharmacyStockList;
    }

    public void setPharmacyStockList(List<PharmacyStock> pharmacyStockList) {
        this.pharmacyStockList = pharmacyStockList;
    }

    public List<RequestForAdditional> getRequestForAdditional() {
        return requestForAdditional;
    }

    public void setRequestForAdditional(List<RequestForAdditional> requestForAdditional) {
        this.requestForAdditional = requestForAdditional;
    }
}
