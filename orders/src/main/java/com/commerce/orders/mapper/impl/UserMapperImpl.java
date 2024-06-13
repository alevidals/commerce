package com.commerce.orders.mapper.impl;

import com.commerce.orders.dto.UserDto;
import com.commerce.orders.mapper.UserMapper;
import com.commerce.orders.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .document(userDto.getDocument())
                .name(userDto.getName())
                .surnames(userDto.getSurnames())
                .build();
    }

    @Override
    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .document(user.getDocument())
                .name(user.getName())
                .surnames(user.getSurnames())
                .build();
    }
}
