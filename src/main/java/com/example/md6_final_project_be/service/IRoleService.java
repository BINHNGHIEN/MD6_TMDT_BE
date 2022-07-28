package com.example.md6_final_project_be.service;

import com.example.md6_final_project_be.model.Role;
import com.example.md6_final_project_be.model.RoleName;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
