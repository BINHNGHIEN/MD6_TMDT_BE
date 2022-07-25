package com.example.md6_final_project_be.service;

import com.example.md6_final_project_be.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IProductService {
    Iterable<Product> findByName(String name);
    Optional<Product> findById(Long id);
    Product save(Product product);
    Iterable<Product> findByCategory(String name);
    void deleteProduct(Long id);
    Iterable<Product> findAllProduct();
}
