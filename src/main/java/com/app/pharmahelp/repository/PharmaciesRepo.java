package com.app.pharmahelp.repository;

import com.app.pharmahelp.entities.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PharmaciesRepo extends JpaRepository<Pharmacy, Long> {
    List<Pharmacy> findByLocation(String location);

    @Transactional
    @Modifying
    @Query("update Pharmacy p set p.location=?1, p.name=?2 where p.id_pharmacy = ?3")
    int update(String location, String name, long id_pharmacy);

    @Transactional
    @Modifying
    @Query("delete from Pharmacy p where p.id_pharmacy = ?1")
    int deletePharmacyById(long id_pharmacy);
}
