package com.testtask.demo.service.mapper;

import com.testtask.demo.model.Account;
import com.testtask.demo.model.User;
import com.testtask.demo.model.dto.AccountRequestDto;
import com.testtask.demo.model.dto.AccountResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account makeEntity(AccountRequestDto accountRequestDto, User user) {
        return Account.builder().accountNumber(accountRequestDto.getAccountNum())
                .accountType(accountRequestDto.getAccountType())
                .balance(accountRequestDto.getBalance()).user(user).build();
    }

    public AccountResponseDto makeDto(Account account) {
        return AccountResponseDto.builder().accountId(account.getId())
                .accountNum(account.getAccountNumber()).accountType(account.getAccountType())
                .balance(account.getBalance()).build();
    }
}
