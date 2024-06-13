package com.commerce.orders.service.impl;

import com.commerce.orders.exception.OrderNotFoundException;
import com.commerce.orders.exception.OrderWrongTotalAmountException;
import com.commerce.orders.model.Item;
import com.commerce.orders.model.Order;
import com.commerce.orders.model.User;
import com.commerce.orders.repository.OrderRepository;
import com.commerce.orders.service.ItemService;
import com.commerce.orders.service.OrderService;
import com.commerce.orders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final ItemService itemService;
    private final OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, UUID> kafkaTemplate;

    @Value("${spring.kafka.order}")
    private String orderTopic;

    public OrderServiceImpl(UserService userService, ItemService itemService, OrderRepository orderRepository) {
        this.userService = userService;
        this.itemService = itemService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        User user = userService.findOne(order.getUser().getId());
        order.setUser(user);

        order.getOrderItems().forEach(orderItem -> {
            Item item = itemService.findOne(orderItem.getItem().getId());
            orderItem.setItem(item);
        });

        Double totalAmount = order.getOrderItems().stream()
                .mapToDouble(orderItem -> orderItem.getItem().getPrice() * orderItem.getQuantity()).sum();

        if (!totalAmount.equals(order.getTotalAmount())) {
            throw new OrderWrongTotalAmountException(
                    String.format("The total amount should be %.2f but was sent %.2f", totalAmount, order.getTotalAmount())
            );
        }

        Order savedOrder = orderRepository.save(order);

        kafkaTemplate.send(orderTopic, savedOrder.getId());

        return savedOrder;
    }

    @Override
    public List<Order> findAll() {
        return StreamSupport.stream(
                orderRepository.findAll().spliterator(), false
        ).toList();
    }

    @Override
    public Order findOne(UUID id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Order not found with id " + id)
        );
    }

    @Override
    public boolean exists(UUID id) {
        return orderRepository.existsById(id);
    }

    @Override
    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }
}
