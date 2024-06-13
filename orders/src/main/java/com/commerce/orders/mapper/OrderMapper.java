package com.commerce.orders.mapper;

import com.commerce.orders.dto.OrderDto;
import com.commerce.orders.model.Order;

public interface OrderMapper {
    Order orderDtoToOrder(OrderDto orderDto);

    OrderDto orderToOrderDto(Order order);
}
