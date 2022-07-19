package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.model.AppUser;
import com.example.md6_final_project_be.service.user.IUserService;
import com.example.md6_final_project_be.service.user.UserServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
