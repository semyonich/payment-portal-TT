package com.testtask.demo.service;

import com.testtask.demo.model.Account;
import java.util.List;

public interface AccountService {
    Account create(Account account);

    List<Account> createAll(List<Account> accounts);

    List<Account> getAllByUserId(Long userId);

    Account getById(Long id);
}
