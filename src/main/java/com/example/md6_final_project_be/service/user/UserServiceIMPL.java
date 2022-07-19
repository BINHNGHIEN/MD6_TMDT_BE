package com.example.md6_final_project_be.service.user;

import com.example.md6_final_project_be.model.AppUser;
import com.example.md6_final_project_be.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIMPL implements IUserService{
    @Autowired
    IUserRepository userRepository;
    public Iterable<AppUser> findAll() {
        return userRepository.findAll();
    }
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<AppUser> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Page<AppUser> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Iterable<AppUser> findAllCustomer() {
        return userRepository.findAllCustomer();
    }
}
