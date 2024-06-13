package com.commerce.orders.service;

import com.commerce.orders.model.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemService {
    Item save(Item item);

    List<Item> findAll();

    Optional<Item> findOne(UUID id);

    boolean exists(UUID id);

    Item update(UUID id, Item item);

    void delete(UUID id);
}
