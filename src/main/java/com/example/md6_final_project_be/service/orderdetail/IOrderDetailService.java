package com.example.md6_final_project_be.service.orderdetail;

import com.example.md6_final_project_be.model.Order;
import com.example.md6_final_project_be.model.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IOrderDetailService {
    Iterable<OrderDetail> findAll();
    Optional<OrderDetail> findById(Long id);
    void save(OrderDetail orderDetail);
    void deleteOrderDetail(Long id);
}
