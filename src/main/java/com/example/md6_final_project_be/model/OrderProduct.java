package com.example.md6_final_project_be.model;

import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameProduct;
    private String avatarProduct;
    private int priceProduct;



}
