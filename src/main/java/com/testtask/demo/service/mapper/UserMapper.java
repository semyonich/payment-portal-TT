package com.testtask.demo.service.mapper;

import com.testtask.demo.model.User;
import com.testtask.demo.model.dto.UserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User makeEntity(UserRequestDto userRequestDto) {
        return User.builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .build();

    }
}
