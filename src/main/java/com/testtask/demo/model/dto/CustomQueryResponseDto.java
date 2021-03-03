package com.testtask.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomQueryResponseDto {
    @Data
    @Builder
    public static class Payer {
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
    }

    @Data
    @Builder
    public static class Recipient {
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
    }

    private Long id;
    private String timestamp;
    private String status;
    @JsonProperty("source_acc_num")
    private String srcAccNum;
    @JsonProperty("dest_acc_num")
    private String destAccNum;
    private Double amount;
    private Payer payer;
    private Recipient recipient;
}
