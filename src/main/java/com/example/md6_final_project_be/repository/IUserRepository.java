package com.example.md6_final_project_be.repository;

import com.example.md6_final_project_be.model.Role;
import com.example.md6_final_project_be.model.RoleName;
import com.example.md6_final_project_be.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


public interface IUserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String name);

    Boolean existsByUsername(String username);

    @Query(value = "SELECT * FROM user LEFT JOIN user_role ON user.id = user_role.user_id WHERE user_role.role_id = 1 AND user.phone LIKE ?1", nativeQuery = true)
    Iterable<User> findCustomerByPhone(String phone);

    Iterable<User> findAllByRolesIn(Set<Role> role, Sort sort);

}

