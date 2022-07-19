package com.example.md6_final_project_be.service.product;

import com.example.md6_final_project_be.model.Product;
import com.example.md6_final_project_be.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceIMPL implements IProductService{
    @Autowired
    IProductRepository productRepository;
    public Optional<Product> findByName(Product nameProduct){
        return productRepository.findByName(nameProduct);
    }
}
