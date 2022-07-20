package com.example.md6_final_project_be.repository;

import com.example.md6_final_project_be.model.AppUser;
import com.example.md6_final_project_be.model.Category;
import com.example.md6_final_project_be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    Iterable<Product> findByNameProductContaining(String name);
    Iterable<Product> findByCategory(Category category);



}
