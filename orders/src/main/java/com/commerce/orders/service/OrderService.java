package com.commerce.orders.service;

import com.commerce.orders.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order save(Order order);

    List<Order> findAll();

    Order findOne(UUID id);

    boolean exists(UUID id);

    void delete(UUID id);
}
