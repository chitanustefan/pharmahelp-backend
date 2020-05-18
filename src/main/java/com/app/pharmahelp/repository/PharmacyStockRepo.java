package com.app.pharmahelp.repository;

import com.app.pharmahelp.entities.PharmacyStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PharmacyStockRepo  extends JpaRepository<PharmacyStock, Long> {

    @Transactional
    @Modifying
    @Query("update PharmacyStock s set s.quantity=?2, s.price=?3 where s.id_pharmacy_stock = ?1")
    void updateQuantityAndPrice(long id_pharmacy_stock, int quantity, Long price);

    @Transactional
    @Modifying
    @Query("update PharmacyStock s set s.quantity=?2 where s.id_pharmacy_stock = ?1")
    void updateQuantity(long id_pharmacy_stock, int quantity);

    @Query("select ps from PharmacyStock ps where ps.pharmacy.id_pharmacy=?1")
    List<PharmacyStock> getPharmacyStocksByIdPharmacy(long id_pharmacy);

    @Query("select ps from PharmacyStock ps where ps.drug.id_drug=?1 and ps.pharmacy.id_pharmacy=?2")
    PharmacyStock getPharmacyStockByDrugAndPharmacy(long id_drug, long id_pharmacy);



}
