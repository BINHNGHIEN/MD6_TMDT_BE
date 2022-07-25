package com.example.md6_final_project_be.repository;

import com.example.md6_final_project_be.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByName(String name);

    Optional<Category> findByName(String name);

    Page<Category> findAllByNameContaining(String name, Pageable pageable);

    @Query("SELECT c FROM Category AS c WHERE c.name LIKE CONCAT('%',:name,'%')")
    Page<Category> findByNameCategoryQuery(@Param("name") String name, Pageable pageable);
}
