package com.testtask.demo.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountRequestDto {
    @NotBlank(message = "accountNum must be non empty or null")
    private String accountNum;
    @NotBlank(message = "accountType must be non empty or null")
    private String accountType;
    @NotNull(message = "balance can't be negative or null")
    @Min(value = 0, message = "balance can't be negative or null")
    private Double balance;
}
