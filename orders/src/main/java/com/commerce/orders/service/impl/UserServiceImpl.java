package com.commerce.orders.service.impl;

import com.commerce.orders.model.User;
import com.commerce.orders.repository.UserRepository;
import com.commerce.orders.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return StreamSupport.stream(
                userRepository.findAll().spliterator(), false
        ).toList();
    }

    @Override
    public Optional<User> findOne(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean exists(UUID id) {
        return userRepository.existsById(id);
    }

    @Override
    public User update(UUID id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(user.getDocument()).ifPresent(existingUser::setDocument);
            Optional.ofNullable(user.getName()).ifPresent(existingUser::setName);
            Optional.ofNullable(user.getSurnames()).ifPresent(existingUser::setSurnames);

            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User does not exists"));
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
