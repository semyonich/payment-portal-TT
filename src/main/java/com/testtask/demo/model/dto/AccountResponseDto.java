package com.testtask.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("account_num")
    private String accountNum;
    @JsonProperty("account_type")
    private String accountType;
    private Double balance;
}
