package com.example.md6_final_project_be.repository;

import com.example.md6_final_project_be.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByNameCategory(String nameCategory);

    Optional<Category> findByNameCategory(String name);

    Page<Category> findAllByNameCategoryContaining(String nameCategory, Pageable pageable);

    @Query("SELECT c FROM Category AS c WHERE c.nameCategory LIKE CONCAT('%',:nameCategory,'%')")
    Page<Category> findByNameCategoryQuery(@Param("nameCategory") String nameCategory, Pageable pageable);
}
