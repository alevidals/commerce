package com.commerce.orders.mapper;

import com.commerce.orders.dto.UserDto;
import com.commerce.orders.model.User;

public interface UserMapper {
    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
