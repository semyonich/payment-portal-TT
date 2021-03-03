package com.testtask.demo.service.mapper;

import com.testtask.demo.model.Account;
import com.testtask.demo.model.Payment;
import com.testtask.demo.model.dto.CreatedPaymentResponseDto;
import com.testtask.demo.model.dto.CustomQueryResponseDto;
import com.testtask.demo.model.dto.PaymentRequestDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentMapper {

    public Payment makeEntity(PaymentRequestDto requestDto,
                              Account sourceAccount, Account destinationAccount) {
        return Payment.builder().sourceAccount(sourceAccount)
                .destinationAccount(destinationAccount).amount(requestDto.getAmount())
                .paymentDate(LocalDateTime.now()).description(requestDto.getReason()).build();
    }

    public CreatedPaymentResponseDto makeCreatedDto(Payment payment) {
        CreatedPaymentResponseDto responseDto = new CreatedPaymentResponseDto();
        responseDto.setPaymentId(payment.getId());
        responseDto.setStatus(payment.getStatus());
        return responseDto;
    }

    public CustomQueryResponseDto makeCustomDto(Payment payment) {
        CustomQueryResponseDto responseDto = CustomQueryResponseDto.builder().id(payment.getId())
                .timestamp(payment.getPaymentDate().toString())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .srcAccNum(payment.getSourceAccount().getAccountNumber())
                .destAccNum(payment.getDestinationAccount().getAccountNumber()).build();
        CustomQueryResponseDto.Payer payer = CustomQueryResponseDto.Payer.builder()
                .firstName(payment.getSourceAccount().getUser().getFirstName())
                .lastName(payment.getSourceAccount().getUser().getLastName()).build();
        CustomQueryResponseDto.Recipient recipient = CustomQueryResponseDto.Recipient
                .builder().firstName(payment.getDestinationAccount().getUser().getFirstName())
                .lastName(payment.getDestinationAccount().getUser().getLastName()).build();
        responseDto.setPayer(payer);
        responseDto.setRecipient(recipient);
        return responseDto;
    }
}
