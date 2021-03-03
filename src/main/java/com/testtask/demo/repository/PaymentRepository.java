package com.testtask.demo.repository;

import com.testtask.demo.model.Payment;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PaymentRepository extends
        JpaRepository<Payment, Long>, QueryByExampleExecutor<Payment> {
    @Override
    <S extends Payment> List<S> findAll(Example<S> example);
}
