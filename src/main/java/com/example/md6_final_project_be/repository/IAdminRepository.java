package com.example.md6_final_project_be.repository;


import com.example.md6_final_project_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IAdminRepository extends JpaRepository<User,Long> {
    Iterable<User> findAppUserByNameContaining(String name);
}
