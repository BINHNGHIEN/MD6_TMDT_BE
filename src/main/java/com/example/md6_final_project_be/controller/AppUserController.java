package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.model.AppUser;
import com.example.md6_final_project_be.service.user.IUserService;
import com.example.md6_final_project_be.service.user.UserServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
public class AppUserController {
    @Autowired
    private UserServiceIMPL userService;

    @GetMapping
    public ResponseEntity<Iterable<AppUser>> findAllCustomer() {
        Iterable<AppUser> customers = userService.findAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/searchbyphonenb")
    public ResponseEntity<Iterable<AppUser>> findCustomerByPhoneNB(@RequestParam(name = "phonenb") String phoneNB) {
        Iterable<AppUser> customers = userService.findCustomerByPhoneNB(phoneNB);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/orderbyname")
    public ResponseEntity<Iterable<AppUser>> findAllCustomerOrderByName() {
        Iterable<AppUser> customers = userService.findAllCustomerOrderByName();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/orderbynamedesc")
    public ResponseEntity<Iterable<AppUser>> findAllCustomerOrderByNameDesc() {
        Iterable<AppUser> customers = userService.findAllCustomerOrderByNameDesc();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/orderbydate")
    public ResponseEntity<Iterable<AppUser>> findAllCustomerOrderByCreateDate() {
        Iterable<AppUser> customers = userService.findAllCustomerOrderByCreateDate();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/orderbydatedesc")
    public ResponseEntity<Iterable<AppUser>> findAllCustomerOrderByCreateDateDesc() {
        Iterable<AppUser> customers = userService.findAllCustomerOrderByCreateDateDesc();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
