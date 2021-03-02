package com.testtask.demo.model.dto;

import lombok.Data;

@Data
public class CreatedPaymentResponseDto {
    private Long paymentId;
    private String status;
}
