package com.commerce.orders.mapper.impl;

import com.commerce.orders.dto.OrderDto;
import com.commerce.orders.dto.OrderItemDto;
import com.commerce.orders.mapper.OrderMapper;
import com.commerce.orders.model.Item;
import com.commerce.orders.model.Order;
import com.commerce.orders.model.OrderItem;
import com.commerce.orders.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order orderDtoToOrder(OrderDto orderDto) {
        User user = User.builder().id(orderDto.getUserId()).build();

        Order order = Order.builder()
                .user(user)
                .totalAmount(orderDto.getTotalAmount())
                .deliveryAddress(orderDto.getDeliveryAddress())
                .build();


        List<OrderItem> orderItems = orderDto.getOrderItems()
                .stream()
                .map(orderItem ->
                        OrderItem.builder()
                                .order(order)
                                .quantity(orderItem.getQuantity())
                                .item(Item.builder().id(orderItem.getItemId()).build())
                                .build()
                ).toList();

        order.setOrderItems(orderItems);

        return order;
    }

    @Override
    public OrderDto orderToOrderDto(Order order) {
        List<OrderItemDto> orderItems = order.getOrderItems().stream()
                .map(orderItem ->
                        OrderItemDto.builder()
                                .itemId(orderItem.getItem().getId())
                                .quantity(orderItem.getQuantity())
                                .build()
                ).toList();

        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .totalAmount(order.getTotalAmount())
                .deliveryAddress(order.getDeliveryAddress())
                .orderItems(orderItems)
                .build();
    }
}
