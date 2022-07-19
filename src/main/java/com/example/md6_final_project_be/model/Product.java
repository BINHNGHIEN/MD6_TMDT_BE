package com.example.md6_final_project_be.model;

import javax.persistence.*;

@Entity
@Table(name = "products",uniqueConstraints ={
        @UniqueConstraint(columnNames = {
                "nameProduct"
        })
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameProduct;
    private String avatarProduct;
    private int priceProduct;
    private int inventoryProduct;
    @ManyToOne
    Category category;

    public Product() {
    }

    public Product(long id, String nameProduct, String avatarProduct, int priceProduct, int inventoryProduct, com.example.md6_final_project_be.model.Category category) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.avatarProduct = avatarProduct;
        this.priceProduct = priceProduct;
        this.inventoryProduct = inventoryProduct;
        this.category = category;
    }
}
