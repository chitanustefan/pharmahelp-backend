package com.app.pharmahelp.repository;

import com.app.pharmahelp.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DrugRepo extends JpaRepository<Drug, Long> {
    Drug findById(long id);
    Drug findByName(String name);

    @Transactional
    @Modifying
    @Query("update Drug d set d.status=?2 where d.id_drug = ?1")
    void validateDrug(long id_drug, String status);
    @Transactional
    @Modifying
    @Query("update Drug d set d.details=?1, d.name= ?2, d.prospectus=?3, d.activeSubstance=?4 where d.id_drug = ?5")
    int update(String details, String name, String prospectus, String activeSubstance, long id_drug);

    @Transactional
    @Modifying
    @Query("delete from Drug d  where d.id_drug = ?1")
    int deleteDrugById(long id_drug);
    Drug findByNameAndDetails(String name,String detail);

}
