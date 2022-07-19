package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.dto.response.ResponseMessage;
import com.example.md6_final_project_be.model.User;
import com.example.md6_final_project_be.model.Role;
import com.example.md6_final_project_be.model.RoleName;
import com.example.md6_final_project_be.security.jwt.JwtProvider;
import com.example.md6_final_project_be.security.jwt.JwtTokenFilter;
import com.example.md6_final_project_be.service.admin.IAdminService;
import com.example.md6_final_project_be.service.role.RoleServiceIMPL;
import com.example.md6_final_project_be.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    IAdminService adminServiceIMPL;
    @Autowired
    RoleServiceIMPL roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    // tim kiem nhân viên theo tên
    @GetMapping("/search/{name}")
    public ResponseEntity<?> findUserByName(@PathVariable String name) {
        List<User> appUserList = (List<User>) adminServiceIMPL.findAppUserByNameContaining(name);
        if (appUserList.isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("user_not_found"), HttpStatus.NOT_FOUND);
        } else {
            List<User> appUserList1 = new ArrayList<>();
            for (int i = 0; i < appUserList.size(); i++) {
                List<Role> roles = new ArrayList<>(appUserList.get(i).getRoles());
                for (int j = 0; j < roles.size(); j++) {
                    if (roles.get(j).getName().equals(RoleName.PM)) {
                        appUserList1.add(appUserList.get(i));
                    }
                }
            }
            return new ResponseEntity<>(appUserList1, HttpStatus.OK);
        }
    }
}
