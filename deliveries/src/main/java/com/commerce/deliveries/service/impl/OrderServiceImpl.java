package com.commerce.deliveries.service.impl;

import com.commerce.deliveries.exception.OrderNotFoundException;
import com.commerce.deliveries.model.Order;
import com.commerce.deliveries.repository.OrderRepository;
import com.commerce.deliveries.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findOne(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("Order not found with id " + orderId)
        );
    }
}
