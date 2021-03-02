package com.testtask.demo.model.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank(message = "FirstName can't be null or empty")
    private String firstName;
    @NotBlank(message = "LastName can't be null or empty")
    private String lastName;
    private List<AccountRequestDto> accounts;
}
