package com.commerce.deliveries.service.impl;

import com.commerce.deliveries.model.Delivery;
import com.commerce.deliveries.model.Order;
import com.commerce.deliveries.repository.DeliveryRepository;
import com.commerce.deliveries.service.DeliveryService;
import com.commerce.deliveries.service.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderService orderService;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, OrderService orderService) {
        this.deliveryRepository = deliveryRepository;
        this.orderService = orderService;
    }

    @Override
    public Delivery save(UUID orderId) {
        Order order = orderService.findOne(orderId);

        Delivery delivery = Delivery.builder()
                .order(order)
                .trackingNumber(RandomStringUtils.randomAlphanumeric(10).toUpperCase())
                .deliveryAddress(order.getDeliveryAddress())
                .build();

        return deliveryRepository.save(delivery);
    }
}
