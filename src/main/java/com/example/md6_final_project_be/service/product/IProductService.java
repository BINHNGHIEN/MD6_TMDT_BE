package com.example.md6_final_project_be.service.product;

import com.example.md6_final_project_be.model.Product;
import com.example.md6_final_project_be.model.Role;
import com.example.md6_final_project_be.model.RoleName;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IProductService {
    Iterable<Product> findByName(String name);
    Optional<Product> findById(Long id);
    void save(Product product);
    Iterable<Product> findByCategory(String name);
    void deleteProduct(Long id);
    Iterable<Product> findAllProduct();
}
