package com.app.pharmahelp.repository;

import com.app.pharmahelp.entities.Pharmacist;
import com.app.pharmahelp.entities.Pharmacy;
import com.app.pharmahelp.entities.PharmacyStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacistRepo  extends JpaRepository<Pharmacist, Long> {

    @Query("select p.pharmacy from Pharmacist p where p.user.id_user=?1")
    Pharmacy getPharmacyByPharmacist(long id_user);
}
