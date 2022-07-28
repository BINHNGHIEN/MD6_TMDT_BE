package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.dto.response.ResponseMessage;

import com.example.md6_final_project_be.model.Order;
import com.example.md6_final_project_be.model.OrderDetail;
import com.example.md6_final_project_be.model.User;
import com.example.md6_final_project_be.service.order.OrderService;
import com.example.md6_final_project_be.service.orderdetail.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailServive;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/list")
    public ResponseEntity<Iterator<OrderDetail>> getListOrderDetail() {
        Iterable<OrderDetail> orderDetailList =  orderDetailServive.findAll();
        if (orderDetailList.equals("")) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<OrderDetail>>(orderDetailList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createOrderDetail(@PathVariable User userID, @RequestBody OrderDetail orderDetail) {
        User appUser = userID.getCurrentUser();
        if (!appUser.getUsername().equals("Anonymous")) {
            if (orderServive.existsByNameOrderDetail(orderDetail.getName())) {
                return new ResponseEntity<>(new ResponseMessage("no_name_orderDetail"), HttpStatus.OK);
            }
            if (orderDetail.getAvatar() == null) {
                orderDetail.setAvatar("https://firebasestorage.googleapis.com/v0/b/chinhbeo-18d3b.appspot.com/o/avatar.png?alt=media&token=3511cf81-8df2-4483-82a8-17becfd03211");
            }
            orderDetailServive.save(orderDetail);
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("create_failed"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<> deleteOrderDetail(@PathVariable Long id) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailServive.findById(id);
        if (!optionalOrderDetail.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetailServive.deleteById(orderDetail.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success!"), HttpStatus.OK);
    }

    @GetMapping("/search/{nameOrderDetail}")
    public ResponseEntity<?> searchByNameOrderDetail(@PathVariable String nameOrderDetail, @PageableDefault(sort = "nameOrderDetail", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<OrderDetail> orderDetailPage = orderDetailServive.findByNameOrderDetailQuery(nameOrderDetail, pageable);
        if (orderDetailPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetailPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailOrder(@PathVariable Long id) {
        Optional<OrderDetail> orderDetail = orderDetailServive.findById(id);
        if (!orderDetail.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        Optional<OrderDetail> orderDetail1 = orderDetailService.findById(id);
        if (!orderDetail1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (orderDetailService.existsByNameOrderDetail(orderDetail.getName())) {
            if (!orderDetail.getAvatar().equals(orderDetail1.get().getAvatar())) {
                orderDetail1.get().setAvatar(orderDetail.getAvatar());
                orderDetailService.save(orderDetail1.get());
                return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ResponseMessage("no_name_orderDetail"), HttpStatus.OK);
        }
        orderDetail1.get().setName(orderDetail.getName());
        orderDetail1.get().setAvatar(orderDetail.getAvatar());
        orderDetailService.save(orderDetail1.get());
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }


}
