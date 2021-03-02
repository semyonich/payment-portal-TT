package com.testtask.demo.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequestDto {
    @NotNull(message = "sourceAccId must be greater than 0")
    @Min(value = 1, message = "sourceAccId must be greater than 0")
    private Long sourceAccId;
    @NotNull(message = "destAccId must be greater than 0")
    @Min(value = 1, message = "destAccId must be greater than 0")
    private Long destAccId;
    @NotNull(message = "amount must be greater than 0")
    @Min(value = 1, message = "amount must be greater than 0")
    private Double amount;
    private String reason;
}
