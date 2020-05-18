package com.app.pharmahelp.repository;

import com.app.pharmahelp.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ReviewRepo  extends JpaRepository<Reviews, Long> {
    @Transactional
    @Modifying
    @Query("update Reviews a set a.status=?2 where a.id_review = ?1")
    void validate(long id_review, String status);
}
