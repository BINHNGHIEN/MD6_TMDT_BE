package com.example.md6_final_project_be.service.admin;

import com.example.md6_final_project_be.model.User;
import org.springframework.stereotype.Service;


@Service
public interface IAdminService  {
    Iterable<User> findAppUserByNameContaining(String name);
}
