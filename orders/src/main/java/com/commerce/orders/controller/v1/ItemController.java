package com.commerce.orders.controller.v1;

import com.commerce.orders.dto.ItemDto;
import com.commerce.orders.exception.ItemNotFoundException;
import com.commerce.orders.mapper.ItemMapper;
import com.commerce.orders.model.Item;
import com.commerce.orders.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        Item item = itemMapper.itemDtoToItem(itemDto);
        Item savedItem = itemService.save(item);

        return new ResponseEntity<>(itemMapper.itemToItemDto(savedItem), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ItemDto> getItems() {
        List<Item> items = itemService.findAll();
        return items.stream().map(itemMapper::itemToItemDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable UUID id) {
        Item item = itemService.findOne(id);

        return new ResponseEntity<>(itemMapper.itemToItemDto(item), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(
            @PathVariable UUID id,
            @RequestBody ItemDto itemDto
    ) {
        if (!itemService.exists(id)) {
            throw new ItemNotFoundException("Item not found with id " + id);
        }

        Item item = itemMapper.itemDtoToItem(itemDto);
        Item savedItem = itemService.update(id, item);
        return new ResponseEntity<>(itemMapper.itemToItemDto(savedItem), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable UUID id) {
        if (!itemService.exists(id)) {
            throw new ItemNotFoundException("Item not found with id " + id);
        }

        itemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
