package com.testtask.demo.controller;

import com.testtask.demo.model.Account;
import com.testtask.demo.model.User;
import com.testtask.demo.model.dto.CreatedUserResponseDto;
import com.testtask.demo.model.dto.UserRequestDto;
import com.testtask.demo.service.AccountService;
import com.testtask.demo.service.UserService;
import com.testtask.demo.service.mapper.AccountMapper;
import com.testtask.demo.service.mapper.UserMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AccountService accountService;
    private final UserMapper userMapper;
    private final AccountMapper accountMapper;

    @PostMapping
    public ResponseEntity<CreatedUserResponseDto>
            createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.makeEntity(userRequestDto);
        List<Account> accounts = userRequestDto.getAccounts().stream()
                .map(a -> accountMapper.makeEntity(a, user))
                .collect(Collectors.toList());
        userService.create(user);
        accountService.createAll(accounts);
        CreatedUserResponseDto responseDto = new CreatedUserResponseDto();
        responseDto.setClientId(user.getId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
