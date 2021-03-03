package com.testtask.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @NotBlank(message = "account_num must be non empty or null")
    @JsonProperty("account_num")
    private String accountNum;
    @NotBlank(message = "account_type must be non empty or null")
    @JsonProperty("account_type")
    private String accountType;
    @NotNull(message = "balance can't be negative or null")
    @Min(value = 0, message = "balance can't be negative or null")
    private Double balance;
}
