package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.model.Order;
import com.example.md6_final_project_be.service.order.IOrderService;
import com.example.md6_final_project_be.service.orderdetail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;


    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrdersById(@PathVariable Long id) {
        Optional<Order> ordersOptional = orderService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {
        order.setCreateDate(new Date());
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable Long id){
        Optional<Order> ordersOptional = orderService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            orderService.deleteOrder(id);
        }
        return new ResponseEntity<>(ordersOptional.get(),HttpStatus.OK);
    }

}
