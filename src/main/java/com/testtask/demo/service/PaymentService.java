package com.testtask.demo.service;

import com.testtask.demo.model.Payment;
import java.util.List;
import org.springframework.data.domain.Example;

public interface PaymentService {
    Payment create(Payment payment);

    List<Payment> createAll(List<Payment> payments);

    List<Payment> findAllByCustomParam(Example<Payment> example);
}
