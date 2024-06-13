package com.commerce.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private UUID id;
    private UUID userId;
    private Double totalAmount;
    private String deliveryAddress;
    private List<OrderItemDto> orderItems;

}
