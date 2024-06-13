package com.commerce.orders.controller.v1;

import com.commerce.orders.dto.OrderDto;
import com.commerce.orders.exception.OrderNotFoundException;
import com.commerce.orders.mapper.OrderMapper;
import com.commerce.orders.model.Order;
import com.commerce.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        Order savedOrder = orderService.save(order);

        return new ResponseEntity<>(orderMapper.orderToOrderDto(savedOrder), HttpStatus.CREATED);
    }

    @GetMapping
    public List<OrderDto> getOrders() {
        List<Order> orders = orderService.findAll();

        return orders.stream()
                .map(orderMapper::orderToOrderDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable UUID id) {
        Order order = orderService.findOne(id);

        return new ResponseEntity<>(orderMapper.orderToOrderDto(order), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable UUID id) {
        if (!orderService.exists(id)) {
            throw new OrderNotFoundException("Order not found with id " + id);
        }

        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
