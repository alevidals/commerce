package com.commerce.deliveries.repository;

import com.commerce.deliveries.model.Delivery;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DeliveryRepository extends CrudRepository<Delivery, UUID> {
}
