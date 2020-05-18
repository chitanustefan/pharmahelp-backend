package com.app.pharmahelp.repository;

import com.app.pharmahelp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.user.id_user=?1")
    List<Order> getOrdersByUser(long id_user);
}
