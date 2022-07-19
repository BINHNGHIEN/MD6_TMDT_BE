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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getAvatarProduct() {
        return avatarProduct;
    }

    public void setAvatarProduct(String avatarProduct) {
        this.avatarProduct = avatarProduct;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getInventoryProduct() {
        return inventoryProduct;
    }

    public void setInventoryProduct(int inventoryProduct) {
        this.inventoryProduct = inventoryProduct;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
