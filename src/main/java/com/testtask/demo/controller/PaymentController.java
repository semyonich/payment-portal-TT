package com.testtask.demo.controller;

import com.testtask.demo.model.Account;
import com.testtask.demo.model.Payment;
import com.testtask.demo.model.User;
import com.testtask.demo.model.dto.CreatedPaymentResponseDto;
import com.testtask.demo.model.dto.CustomQueryResponseDto;
import com.testtask.demo.model.dto.PaymentRequestDto;
import com.testtask.demo.service.PaymentService;
import com.testtask.demo.service.mapper.PaymentMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentMapper mapper;
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<CreatedPaymentResponseDto>
            createPayment(@Valid @RequestBody PaymentRequestDto requestDto) {
        Payment payment = mapper.makeEntity(requestDto);
        paymentService.create(payment);
        CreatedPaymentResponseDto responseDto = mapper.makeCreatedDto(payment);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<CreatedPaymentResponseDto>>
            createPayments(@Valid @RequestBody List<PaymentRequestDto> requestDtos) {
        List<CreatedPaymentResponseDto> payments = requestDtos.stream()
                .map(mapper::makeEntity)
                .map(paymentService::create)
                .map(mapper::makeCreatedDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomQueryResponseDto>>
            findAll(@Valid @RequestParam(required = false, name = "payer_id") Long payerId,
            @RequestParam(required = false, name = "recipient_id") Long recipientId,
            @RequestParam(required = false, name = "source_acc_id") Long sourceId,
            @RequestParam(required = false, name = "dest_acc_id") Long destinationId) {
        Payment examplePayment = new Payment();
        Account sourceAccount = new Account();
        sourceAccount.setId(sourceId);
        examplePayment.setSourceAccount(sourceAccount);
        Account destAccount = new Account();
        destAccount.setId(destinationId);
        examplePayment.setDestinationAccount(destAccount);
        User payerUser = new User();
        payerUser.setId(payerId);
        if (examplePayment.getSourceAccount() == null) {
            examplePayment.setSourceAccount(sourceAccount);
        }
        examplePayment.getSourceAccount().setUser(payerUser);
        User recipientUser = new User();
        recipientUser.setId(recipientId);
        if (examplePayment.getDestinationAccount() == null) {
            examplePayment.setDestinationAccount(destAccount);
        }
        examplePayment.getDestinationAccount().setUser(recipientUser);
        Example<Payment> example = Example.of(examplePayment);
        List<CustomQueryResponseDto> allPayments = paymentService.findAllByCustomParam(example)
                       .stream()
                .map(mapper::makeCustomDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allPayments, HttpStatus.OK);
    }
}
