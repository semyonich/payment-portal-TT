package com.testtask.demo.repository;

import com.testtask.demo.model.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> getAccountsByUser_Id(Long userId);

    Optional<Account> getAccountById(Long id);
}
