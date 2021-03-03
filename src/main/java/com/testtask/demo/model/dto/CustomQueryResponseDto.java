package com.testtask.demo.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomQueryResponseDto {
    @Data
    @Builder
    public static class Payer {
        private String firstName;
        private String lastName;
    }

    @Data
    @Builder
    public static class Recipient {
        private String firstName;
        private String lastName;
    }

    private Long id;
    private String timestamp;
    private String status;
    private String srcAccNum;
    private String destAccNum;
    private Double amount;
    private Payer payer;
    private Recipient recipient;
}
