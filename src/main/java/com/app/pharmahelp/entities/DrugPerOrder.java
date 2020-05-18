package com.app.pharmahelp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "drug_per_oder")
public class DrugPerOrder {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_drug_per_oder", unique = true, nullable = false)
    private Long id_drug_per_oder;

    @Column
    private int total;

    @Column
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Drug drug;

    public Long getId_drug_per_oder() {
        return id_drug_per_oder;
    }

    public void setId_drug_per_oder(Long id_drug_per_oder) {
        this.id_drug_per_oder = id_drug_per_oder;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
