package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.model.OrderDetail;
import com.example.md6_final_project_be.service.order.IOrderService;
import com.example.md6_final_project_be.service.orderdetail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ordersDetail")
@CrossOrigin("*")
public class OrderDetailController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;


    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> findOrdersById(@PathVariable Long id) {
        Optional<OrderDetail> ordersDetailOptional = orderDetailService.findById(id);
        if (!ordersDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersDetailOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDetail> save(@RequestBody OrderDetail orderDetail) {
        orderDetailService.save(orderDetail);
        return new ResponseEntity<>(orderDetail, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDetail> delete(@PathVariable Long id){
        Optional<OrderDetail> ordersDetailOptional = orderDetailService.findById(id);
        if (!ordersDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            orderDetailService.deleteOrderDetail(id);
        }
        return new ResponseEntity<>(ordersDetailOptional.get(),HttpStatus.OK);
    }

}
