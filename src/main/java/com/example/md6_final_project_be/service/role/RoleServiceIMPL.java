package com.example.md6_final_project_be.service.role;

import com.example.md6_final_project_be.model.Role;
import com.example.md6_final_project_be.model.RoleName;
import com.example.md6_final_project_be.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceIMPL implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
