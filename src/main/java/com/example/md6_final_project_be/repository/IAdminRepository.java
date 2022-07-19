package com.example.md6_final_project_be.repository;


import com.example.md6_final_project_be.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<AppUser,Long> {
    Iterable<AppUser> findAppUserByNameContaining(String name);
}
