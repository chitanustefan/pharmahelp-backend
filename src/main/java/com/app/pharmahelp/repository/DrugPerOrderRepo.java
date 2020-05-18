package com.app.pharmahelp.repository;

import com.app.pharmahelp.entities.DrugPerOrder;
import com.app.pharmahelp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugPerOrderRepo extends JpaRepository<DrugPerOrder, Long> {
}
