package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.dto.response.ResponseMessage;
import com.example.md6_final_project_be.model.User;
import com.example.md6_final_project_be.model.Role;
import com.example.md6_final_project_be.model.RoleName;
import com.example.md6_final_project_be.security.jwt.JwtProvider;
import com.example.md6_final_project_be.security.jwt.JwtTokenFilter;
import com.example.md6_final_project_be.service.IAdminService;
import com.example.md6_final_project_be.service.RoleServiceIMPL;
import com.example.md6_final_project_be.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    // thăng chức nhân viên từ user->PM và PM->Admin
    @PutMapping("/upRole/{id}")
    public ResponseEntity<?> changeRolesUp(@PathVariable Long id){
        Optional<User> changUser = userService.findById(id);
        Set<Role> roles = changUser.get().getRoles();
        if(roles.contains(roleService.findByName(RoleName.USER).get())){
            if (roles.contains(roleService.findByName(RoleName.PM).get())){
                if (roles.contains(roleService.findByName(RoleName.ADMIN).get())){
                    return new ResponseEntity<>(new ResponseMessage("Role cao nhất rồi"),HttpStatus.OK);
                }else {
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(adminRole);
                    changUser.get().setRoles(roles);
                }
            }else {
                Role pmRole = roleService.findByName(RoleName.PM).orElseThrow(() -> new RuntimeException("Role not found"));
                roles.add(pmRole);
                changUser.get().setRoles(roles);
            }
        }
        userService.save(changUser.get());
        return new ResponseEntity<>(changUser,HttpStatus.OK);
    }

    // tụt chức nhân viên từ  PM --> user và  Admin --> Pm
    @PutMapping("/downRole/{id}")
    public ResponseEntity<?> changeRolesDown(@PathVariable Long id){
        Optional<User> changUser = userService.findById(id);
        Set<Role> roles = changUser.get().getRoles();
        if(roles.contains(roleService.findByName(RoleName.USER).get())){
            if (roles.contains(roleService.findByName(RoleName.PM).get())){
                if (roles.contains(roleService.findByName(RoleName.ADMIN).get())){
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.remove(adminRole);
                    changUser.get().setRoles(roles);
                }else {
                    Role pmRole = roleService.findByName(RoleName.PM).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.remove(pmRole);
                    changUser.get().setRoles(roles);
                }
            }else {
                return new ResponseEntity<>(new ResponseMessage("not down"),HttpStatus.OK);
            }
        }
        userService.save(changUser.get());
        return new ResponseEntity<>(changUser,HttpStatus.OK);
    }

    // xóa tài khoản
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deletePm(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // xem chi tiết nhân viên
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detailPm(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
