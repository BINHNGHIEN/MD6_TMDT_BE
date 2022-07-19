package com.example.md6_final_project_be.service.admin;

import com.example.md6_final_project_be.model.AppUser;
import com.example.md6_final_project_be.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceIMPL implements IAdminService{
    @Autowired
    IAdminRepository adminRepository;
    public Iterable<AppUser> findAppUserByNameContaining(String name) {
        return adminRepository.findAppUserByNameContaining(name);
    }
}
