package com.zenltd.dto;

import com.zenltd.entity.Address;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
public class MemberDto {
    private long id;

    @NotBlank(message = "name cannot be blank")
    private String name;

//    @NotNull(message = "address cannot be null")
    @Valid
    private AddressDto addressDto;

    @NotBlank(message = "gstNo cannot be blank")
    @Size(min = 15, max = 15, message = "gstNo must be 15 digits alphanumeric code")
    private String gstNo;

    @NotBlank(message = "representativeName cannot be blank")
    private String representativeName;

    @NotNull(message = "phoneNo cannot be null")
    @Size(min = 10, max = 13, message = "phoneNo must be 10 digits alphanumeric code")
    private String phoneNo;

    @NotBlank(message = "emailId cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
                        message = "IInvalid email format")
    private String emailId;

    @NotBlank(message = "addressProofId cannot be blank")
    private String addressProofId;

    @NotBlank(message = "constitution cannot be blank")
    private String constitution;

    @NotBlank(message = "panNo cannot be blank")
    @Size(min = 10, max = 10, message = "panNo must be 10 digits alphanumeric code")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]", message = "Invalid PAN format. Must be 5 uppercase letters, 4 digits, and 1 uppercase letter")
    private String panNo;
}
