package com.app.pharmahelp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reviews")
public class Reviews {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_review",unique = true, nullable = false)
    private Long id_review;

    @Column
    private int rating;

    @Column
    private String comment;

    @Column
    private Date createdDate;

    @Column
    private String status;

//    @OneToOne(cascade = {CascadeType.ALL})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Drug drug;

    public Long getId_review() {
        return id_review;
    }

    public void setId_review(Long id_review) {
        this.id_review = id_review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
