package com.testtask.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentRequestDto {
    @NotNull(message = "source_acc_id must be greater than 0")
    @Min(value = 1, message = "source_acc_id must be greater than 0")
    @JsonProperty("source_acc_id")
    private Long sourceAccId;
    @NotNull(message = "dest_acc_id must be greater than 0")
    @Min(value = 1, message = "dest_acc_id must be greater than 0")
    @JsonProperty("dest_acc_id")
    private Long destAccId;
    @NotNull(message = "amount must be greater than 0")
    @Min(value = 1, message = "amount must be greater than 0")
    private Double amount;
    private String reason;
}
