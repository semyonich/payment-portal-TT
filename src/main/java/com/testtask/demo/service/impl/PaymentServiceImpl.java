package com.testtask.demo.service.impl;

import com.testtask.demo.model.Account;
import com.testtask.demo.model.Payment;
import com.testtask.demo.repository.AccountRepository;
import com.testtask.demo.repository.PaymentRepository;
import com.testtask.demo.service.PaymentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Payment create(Payment payment) {
        if (payment.getSourceAccount().getBalance() < payment.getAmount()) {
            payment.setStatus("error");
            return paymentRepository.save(payment);
        }
        Double sum = payment.getAmount();
        Account source = payment.getSourceAccount();
        Double newSourceBalance = source.getBalance() - sum;
        source.setBalance(newSourceBalance);
        Account destination = payment.getDestinationAccount();
        Double newDestinationBalance = destination.getBalance() + sum;
        destination.setBalance(newDestinationBalance);
        payment.setStatus("ok");
        accountRepository.saveAll(List.of(source, destination));
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> createAll(List<Payment> payments) {
        return paymentRepository.saveAll(payments);
    }

    @Override
    public List<Payment> findAllByCustomParam(Example<Payment> example) {
        return paymentRepository.findAll(example);
    }
}
