package com.example.md6_final_project_be.service;

import com.example.md6_final_project_be.model.CartElement;
import com.example.md6_final_project_be.model.Product;
import com.example.md6_final_project_be.model.User;
import com.example.md6_final_project_be.repository.ICartElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartElementServiceImpl implements ICartElementService {
    @Autowired
    private ICartElementRepository cartElementRepository;

    @Override
    public Iterable<CartElement> findAllByUser(User user) {
        return cartElementRepository.findAllByUser(user);
    }

    @Override
    public void deleteAllByUser(User user) {
        cartElementRepository.deleteAllByUser(user);
    }

    @Override
    public Iterable<CartElement> findAll() {
        return cartElementRepository.findAll();
    }

    @Override
    public Optional<CartElement> findById(Long id) {
        return cartElementRepository.findById(id);
    }

    @Override
    public CartElement save(CartElement cartElement) {
        return cartElementRepository.save(cartElement);
    }

    @Override
    public void remove(Long id) {
        cartElementRepository.deleteById(id);
    }
}
