package com.zenltd.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AddressDto {
    private long id;
    @NotNull(message = "buildingNo cannot be null")
    @Min(value = 1, message = "buildingNo must be greater than or equal to 1")
    private Integer buildingNo; // Use Integer instead of int to allow null checks
    @NotNull(message = "streetNo cannot be null")
    @Min(value = 1, message = "streetNo must be greater than or equal to 1")
    private Integer streetNo; // Use Integer instead of int to allow null checks
    @NotBlank(message = "city cannot be blank")
    private String city;
    @NotBlank(message = "state cannot be blank")
    private String state;
    @NotNull(message = "pinCode cannot be null")
    @Min(value = 100000,message = "pinCode must be 6 digits")
    @Max (value= 999999, message = "pinCode must be 6 digits")
    private Long pinCode; // Use Integer instead of int to allow null checks
}
