package com.app.pharmahelp.controller;

import com.app.pharmahelp.entities.*;
import com.app.pharmahelp.security.payloads.ApiResponse;
import com.app.pharmahelp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.app.pharmahelp.entities.Order;
import com.app.pharmahelp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    PharmaciesService pharmaciesService;
    @Autowired
    DrugPerOrderService drugPerOrderService;

    @Autowired
    PharmacyStockService pharmacyStockService;

    @PostMapping("/addorder")
    public ResponseEntity<?> registerPharmacy(@RequestBody Order order) {
        Order newOrder = new Order();

        List<User> usersList = userService.getAllUsers();
        List<Pharmacy> pharmacyList = pharmaciesService.getAllPharmacies();

        for (User u : usersList) {
            if (u.getId_user() == order.getUser().getId_user()) {
                newOrder.setUser(u);
            }
        }


        for (Pharmacy p : pharmacyList) {
            if (p.getId_pharmacy() == order.getPharmacy().getId_pharmacy()) {
                newOrder.setPharmacy(p);
                List<PharmacyStock> pharmacyStockList=p.getPharmacyStockList();
                for(PharmacyStock stock : pharmacyStockList){
                    for(DrugPerOrder drugPerOrder: order.getDrugPerOrders()){
                        Drug drug = drugPerOrder.getDrug();
                        if(stock.getDrug().getId_drug() == drug.getId_drug()){
                                int qt = stock.getQuantity()-drugPerOrder.getQuantity();
                                System.out.println("New qt:" + qt);
                                pharmacyStockService.updateQuantity(stock.getId_pharmacy_stock(), qt);
                        }
                    }
                }
            }
        }


        newOrder.setStatus("Accepted!");
        newOrder.setTotalPrice(order.getTotalPrice());
        List<DrugPerOrder> drugPerOrderList = new ArrayList<>();
        for(DrugPerOrder drugper: order.getDrugPerOrders()){
            drugper.setOrder(newOrder);
            drugPerOrderList.add(drugper);
            drugPerOrderService.saveDrugPerOrder(drugper);
        }
        newOrder.setDrugPerOrders(drugPerOrderList);
        orderService.saveOrder(newOrder);
        return ResponseEntity.ok(new ApiResponse(true, "Order successfully registered"));

    }


    @GetMapping("/getOrdersByUser")
    public List<Order> getOrderByUser(@RequestParam long idUser){
        return orderService.getOrdersByUser(idUser);
    }
}
