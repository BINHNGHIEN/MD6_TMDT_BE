package com.example.md6_final_project_be.service.admin;

import com.example.md6_final_project_be.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface IAdminService  {
    Iterable<AppUser> findAppUserByNameContaining(String name);
}
