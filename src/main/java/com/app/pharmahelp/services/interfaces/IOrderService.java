package com.app.pharmahelp.services.interfaces;

import com.app.pharmahelp.entities.Order;

import java.util.List;

public interface IOrderService {

    List<Order> getAllOrders();
    Order saveOrder(Order order);
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);

}
