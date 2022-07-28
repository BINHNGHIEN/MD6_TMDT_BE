package com.example.md6_final_project_be.repository;

import com.example.md6_final_project_be.model.Category;
import com.example.md6_final_project_be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    Iterable<Product> findByNameProductContaining(String name);
    Iterable<Product> findByCategory(Category category);
    @Modifying
    @Query(value = "select * from products where name like CONCAT('%',:name,'%');", nativeQuery = true)
    Iterable<Product> findProductByNameContaining(@Param("name") String name);
}
