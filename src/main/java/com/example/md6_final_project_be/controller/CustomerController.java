package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.dto.response.ResponseMessage;
import com.example.md6_final_project_be.model.User;
import com.example.md6_final_project_be.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Iterable<User>> findAllCustomer() {
        Iterable<User> customers = userService.findAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findCustomerByPhone(@RequestParam(name = "phone") String phone) {
        try {
            Iterable<User> customers = userService.findCustomerByPhone(phone);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessage("Đã xảy ra lỗi hệ thống. Chi tiết: " + e.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("/orderbyname")
    public ResponseEntity<Iterable<User>> findAllCustomerOrderByName() {
        Iterable<User> customers = userService.findAllCustomerOrderByName();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/orderbynamedesc")
    public ResponseEntity<Iterable<User>> findAllCustomerOrderByNameDesc() {
        Iterable<User> customers = userService.findAllCustomerOrderByNameDesc();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/orderbydate")
    public ResponseEntity<Iterable<User>> findAllCustomerOrderByCreateDate() {
        Iterable<User> customers = userService.findAllCustomerOrderByCreateDate();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/orderbydatedesc")
    public ResponseEntity<Iterable<User>> findAllCustomerOrderByCreateDateDesc() {
        Iterable<User> customers = userService.findAllCustomerOrderByCreateDateDesc();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
