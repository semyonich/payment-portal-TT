package com.testtask.demo.service.impl;

import com.testtask.demo.model.User;
import com.testtask.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements com.testtask.demo.service.UserService {
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}
