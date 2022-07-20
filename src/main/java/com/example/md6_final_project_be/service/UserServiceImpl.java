package com.example.md6_final_project_be.service;

import com.example.md6_final_project_be.model.Role;
import com.example.md6_final_project_be.model.RoleName;
import com.example.md6_final_project_be.model.User;
import com.example.md6_final_project_be.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    RoleServiceIMPL roleServiceIMPL;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Iterable<User> findAllCustomer() {
        Set<Role> roles = new HashSet<>();
        Role role = roleServiceIMPL.findByName(RoleName.USER).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        roles.add(role);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return userRepository.findAllByRolesIn(roles, sort);
    }

    @Override
    public Iterable<User> findCustomerByPhone(String phoneNB) {
        return userRepository.findCustomerByPhone(phoneNB);
    }

    @Override
    public Iterable<User> findAllCustomerOrderByName() {
        Set<Role> roles = new HashSet<>();
        Role role = roleServiceIMPL.findByName(RoleName.USER).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        roles.add(role);
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return userRepository.findAllByRolesIn(roles, sort);
    }

    @Override
    public Iterable<User> findAllCustomerOrderByNameDesc() {
        Set<Role> roles = new HashSet<>();
        Role role = roleServiceIMPL.findByName(RoleName.USER).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        roles.add(role);
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        return userRepository.findAllByRolesIn(roles, sort);
    }

    @Override
    public Iterable<User> findAllCustomerOrderByCreateDate() {
        Set<Role> roles = new HashSet<>();
        Role role = roleServiceIMPL.findByName(RoleName.USER).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        roles.add(role);
        Sort sort = Sort.by(Sort.Direction.ASC, "createDate");
        return userRepository.findAllByRolesIn(roles, sort);
    }

    @Override
    public Iterable<User> findAllCustomerOrderByCreateDateDesc() {
        Set<Role> roles = new HashSet<>();
        Role role = roleServiceIMPL.findByName(RoleName.USER).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        roles.add(role);
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
        return userRepository.findAllByRolesIn(roles, sort);
    }
}
