package com.example.md6_final_project_be.service;

import com.example.md6_final_project_be.model.CartElement;
import com.example.md6_final_project_be.model.User;

import java.util.Optional;

public interface ICartElementService {
    Iterable<CartElement> findAll();

    Optional<CartElement> findById(Long id);

    CartElement save(CartElement cartElement);

    void remove(Long id);

    Iterable<CartElement> findAllByUser(User user);

    void deleteAllByUser(User user);
}
