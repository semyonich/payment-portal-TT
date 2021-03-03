package com.testtask.demo.model.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "FirstName can't be null or empty")
    private String firstName;
    @NotBlank(message = "LastName can't be null or empty")
    private String lastName;
    private List<AccountRequestDto> accounts;
}
