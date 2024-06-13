package com.commerce.orders.service;

import com.commerce.orders.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User save(User user);

    List<User> findAll();

    Optional<User> findOne(UUID id);

    boolean exists(UUID id);

    User update(UUID id, User user);

    void delete(UUID id);
}
