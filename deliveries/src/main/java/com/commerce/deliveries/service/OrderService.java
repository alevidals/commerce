package com.commerce.deliveries.service;

import com.commerce.deliveries.model.Order;

import java.util.UUID;

public interface OrderService {
    Order findOne(UUID orderId);
}
