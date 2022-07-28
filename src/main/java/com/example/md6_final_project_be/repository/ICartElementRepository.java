package com.example.md6_final_project_be.repository;

import com.example.md6_final_project_be.model.CartElement;
import com.example.md6_final_project_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartElementRepository extends JpaRepository<CartElement, Long> {
    Iterable<CartElement> findAllByUser(User user);
    void deleteAllByUser(User user);
}
