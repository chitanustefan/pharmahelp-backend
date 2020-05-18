package com.app.pharmahelp.services;

import com.app.pharmahelp.entities.Order;
import com.app.pharmahelp.entities.User;
import com.app.pharmahelp.repository.OrderRepo;
import com.app.pharmahelp.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepo orderRepository;

    @Override
    public List<Order> getAllOrders() {
        List<Order> result = orderRepository.findAll();
        return result;
    }

    @Override
    public Order saveOrder(Order order) {
        Order saved = this.orderRepository.save(order);
        return saved;
    }

    @Override
    @Transactional
    public Order updateOrder(Long id, Order order) {
        Optional<Order> optional = orderRepository.findById(id);
        Order result = optional.orElse(order);
        return result;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getOrdersByUser(long idUser){
        return orderRepository.getOrdersByUser(idUser);
    }
}
