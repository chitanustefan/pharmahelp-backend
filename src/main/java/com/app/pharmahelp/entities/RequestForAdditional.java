package com.app.pharmahelp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "request_for_additional")
public class RequestForAdditional {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_request",unique = true, nullable = false)
    private Long id_request;

    @Column
    private String location;

    @Column
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Drug drug;


    public Long getId_request() {
        return id_request;
    }

    public void setId_request(Long id_request) {
        this.id_request = id_request;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }
}
