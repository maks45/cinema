package com.durov.maks.cinema.model.mapper;

import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.model.dto.user.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto getUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setLogin(user.getLogin());
        return userResponseDto;
    }
}
