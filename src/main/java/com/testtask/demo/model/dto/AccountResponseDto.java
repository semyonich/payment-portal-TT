package com.testtask.demo.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {
    private Long accountId;
    private String accountNum;
    private String accountType;
    private Double balance;
}
