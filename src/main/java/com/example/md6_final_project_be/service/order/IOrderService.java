package com.example.md6_final_project_be.service.order;

import com.example.md6_final_project_be.model.Order;
import com.example.md6_final_project_be.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IOrderService {
    Iterable<Order> findAll();
    Optional<Order> findById(Long id);
    void save(Order order);
    void deleteOrder(Long id);
}
