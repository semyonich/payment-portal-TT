package com.testtask.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreatedUserResponseDto {
    @JsonProperty("client_id")
    private Long clientId;
}
