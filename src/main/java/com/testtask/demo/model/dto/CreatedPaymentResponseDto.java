package com.testtask.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreatedPaymentResponseDto {
    @JsonProperty("payment_id")
    private Long paymentId;
    private String status;
}
