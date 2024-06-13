package com.commerce.deliveries.service;

import com.commerce.deliveries.model.Delivery;

import java.util.UUID;

public interface DeliveryService {
    Delivery save(UUID orderId);
}
