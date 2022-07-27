package com.example.md6_final_project_be.service.product;

import com.example.md6_final_project_be.model.Category;
import com.example.md6_final_project_be.model.Product;
import com.example.md6_final_project_be.repository.IProductRepository;
import com.example.md6_final_project_be.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceIMPL implements IProductService{
    @Autowired
    IProductRepository productRepository;
    @Autowired
    ICategoryService categoryService;
    @Override
    public Iterable<Product> findByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Iterable<Product> findByCategory(String name) {
        Optional<Category> category = categoryService.findByName(name);
        return productRepository.findByCategory(category.get());
    }

    @Override
    public void deleteProduct(Long id) {
    productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findAllProduct(){
        return productRepository.findAll();
    }

}
