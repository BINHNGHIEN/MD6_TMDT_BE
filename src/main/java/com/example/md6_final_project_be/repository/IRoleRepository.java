package com.example.md6_final_project_be.repository;

import com.example.md6_final_project_be.model.Role;
import com.example.md6_final_project_be.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);
}
