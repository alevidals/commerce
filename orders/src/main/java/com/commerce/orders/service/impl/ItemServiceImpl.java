package com.commerce.orders.service.impl;

import com.commerce.orders.exception.ItemNotFoundException;
import com.commerce.orders.model.Item;
import com.commerce.orders.repository.ItemRepository;
import com.commerce.orders.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> findAll() {
        return StreamSupport.stream(
                itemRepository.findAll().spliterator(), false
        ).toList();
    }

    @Override
    public Item findOne(UUID id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found with id " + id));
    }

    @Override
    public boolean exists(UUID id) {
        return itemRepository.existsById(id);
    }

    @Override
    public Item update(UUID id, Item item) {
        return itemRepository.findById(id).map(existingItem -> {
            Optional.ofNullable(item.getName()).ifPresent(existingItem::setName);
            Optional.ofNullable(item.getDescription()).ifPresent(existingItem::setDescription);
            Optional.ofNullable(item.getPrice()).ifPresent(existingItem::setPrice);
            Optional.ofNullable(item.getImageUrl()).ifPresent(existingItem::setImageUrl);

            return itemRepository.save(existingItem);
        }).orElseThrow(() -> new ItemNotFoundException("Item not found with id " + id));
    }

    @Override
    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

}
