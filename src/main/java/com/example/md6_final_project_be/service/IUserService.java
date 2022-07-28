package com.example.md6_final_project_be.service;

import com.example.md6_final_project_be.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);//Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username);//username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email);//email da co trong DB chua
    Page<User> findAll(Pageable pageable); //Page User
    Iterable<User> findAll();
    Iterable<User> findAllCustomer();
    Iterable<User> findCustomerByPhone(String phoneNB);
    Iterable<User> findAllCustomerOrderByName();
    Iterable<User> findAllCustomerOrderByNameDesc();
    Iterable<User> findAllCustomerOrderByCreateDate();
    Iterable<User> findAllCustomerOrderByCreateDateDesc();

}
