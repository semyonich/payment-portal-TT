package com.testtask.demo.controller;

import com.testtask.demo.model.dto.AccountResponseDto;
import com.testtask.demo.service.AccountService;
import com.testtask.demo.service.mapper.AccountMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @GetMapping
    List<AccountResponseDto> getAllByUserId(@RequestParam(name = "client_id") Long clientId) {
        return accountService.getAllByUserId(clientId).stream()
                .map(accountMapper::makeDto)
                .collect(Collectors.toList());
    }
}
