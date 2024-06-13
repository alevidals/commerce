package com.commerce.deliveries.event;

import com.commerce.deliveries.model.Delivery;
import com.commerce.deliveries.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderListener {
    private final DeliveryService deliveryService;
    private Logger LOGGER = LoggerFactory.getLogger(OrderListener.class);

    public OrderListener(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @KafkaListener(topics = {"#{'${spring.kafka.order}'}"}, groupId = "groupId", containerFactory = "consumer")
    public void listener(UUID orderId) {
        Delivery delivery = deliveryService.save(orderId);

        LOGGER.info("The delivery has been processed for the order {} with this tracking number {}",
                delivery.getOrder().getId(),
                delivery.getTrackingNumber()
        );
    }

}
