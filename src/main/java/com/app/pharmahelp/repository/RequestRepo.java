package com.app.pharmahelp.repository;

import com.app.pharmahelp.entities.RequestForAdditional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<RequestForAdditional, Long> {

    List<RequestForAdditional> getAllByLocation(String location);

    @Query("SELECT r FROM RequestForAdditional r WHERE r.location = :location GROUP BY r.drug ORDER BY COUNT(r) DESC" )
    List<RequestForAdditional> mostReqeustDrug(@Param("location") String location);


}
