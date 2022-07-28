package com.example.md6_final_project_be.controller;


import com.example.md6_final_project_be.dto.response.ResponseMessage;

import com.example.md6_final_project_be.model.Order;
import com.example.md6_final_project_be.model.Product;
import com.example.md6_final_project_be.model.User;
import com.example.md6_final_project_be.service.IUserService;
import com.example.md6_final_project_be.service.order.IOrderService;
import com.example.md6_final_project_be.service.order.OrderService;
import com.example.md6_final_project_be.service.orderdetail.IOrderDetailService;
import com.example.md6_final_project_be.service.orderdetail.OrderDetailService;
import com.example.md6_final_project_be.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("/list")
    public ResponseEntity<?> getListOrder() {
        List<Order> orderList = (List<Order>) orderService.findAll();
        if (orderList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Optional<OroptionalOrderDetail= orderDetailService.findById(order.getId());
        return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderServive.findById(id);
        if (!optionalOrder.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(orderServive.deleteById(order.get().getId()), new ResponseMessage("Delete Success!"), HttpStatus.OK);
    }

    @GetMapping("/search/{nameOrder}")
    public ResponseEntity<?> searchByNameOrder(@PathVariable String nameOrder, @PageableDefault(sort = "nameOrder", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Order> orderPage = orderServive.findByNameOrderQuery(nameOrder, pageable);
        if (orderPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.findById(id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOder(@PathVariable Long id, @RequestBody Order order) {
        Optional<Order> optionalOrder = orderServive.findById(id);
        if (!optionalOrder.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (orderServive.existsByNameOrder(order.getName())) {
            if (!order.getAvatar().equals(order1.get().getAvatar())) {
                order1.get().setAvatar(order.getAvatar());
                orderServive.save(order1.get());
                return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ResponseMessage("no_name_order"), HttpStatus.OK);
        }
        order1.get().setName(order.getName());
        order1.get().setAvatar(order.getAvatar());
        orderServive.save(order1.get());
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }


}