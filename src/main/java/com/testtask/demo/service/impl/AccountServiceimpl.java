package com.testtask.demo.service.impl;

import com.testtask.demo.model.Account;
import com.testtask.demo.repository.AccountRepository;
import com.testtask.demo.service.AccountService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceimpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> createAll(List<Account> accounts) {
        return accountRepository.saveAll(accounts);
    }

    @Override
    public List<Account> getAllByUserId(Long userId) {
        return accountRepository.getAccountsByUser_Id(userId);
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.getAccountById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to get account, id=" + id));
    }
}
