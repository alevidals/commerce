package com.commerce.orders.controller.v1;

import com.commerce.orders.dto.UserDto;
import com.commerce.orders.exception.UserNotFoundException;
import com.commerce.orders.mapper.UserMapper;
import com.commerce.orders.model.User;
import com.commerce.orders.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        User savedUser = userService.save(user);

        return new ResponseEntity<>(userMapper.userToUserDto(savedUser), HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        List<User> users = userService.findAll();
        return users.stream().map(userMapper::userToUserDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id) {
        User user = userService.findOne(id);

        return new ResponseEntity<>(userMapper.userToUserDto(user), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable UUID id,
            @RequestBody UserDto userDto
    ) {
        if (!userService.exists(id)) {
            throw new UserNotFoundException("User not found with id " + id);
        }

        User user = userMapper.userDtoToUser(userDto);
        User savedUser = userService.update(id, user);

        return new ResponseEntity<>(userMapper.userToUserDto(savedUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        if (!userService.exists(id)) {
            throw new UserNotFoundException("User not found with id " + id);
        }

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
