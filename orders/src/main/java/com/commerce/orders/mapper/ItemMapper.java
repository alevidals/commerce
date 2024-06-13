package com.commerce.orders.mapper;

import com.commerce.orders.dto.ItemDto;
import com.commerce.orders.model.Item;

public interface ItemMapper {
    Item itemDtoToItem(ItemDto itemDto);

    ItemDto itemToItemDto(Item item);
}
