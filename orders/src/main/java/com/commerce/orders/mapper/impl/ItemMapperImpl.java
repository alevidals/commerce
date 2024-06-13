package com.commerce.orders.mapper.impl;

import com.commerce.orders.dto.ItemDto;
import com.commerce.orders.mapper.ItemMapper;
import com.commerce.orders.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public Item itemDtoToItem(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .price(itemDto.getPrice())
                .imageUrl(itemDto.getImageUrl())
                .build();
    }

    @Override
    public ItemDto itemToItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .imageUrl(item.getImageUrl())
                .build();
    }
}
