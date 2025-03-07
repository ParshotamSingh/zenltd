package com.zenltd.dto;

import com.zenltd.enums.EmployeeAvailabilityStatus;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
@Data
public class EmployeeAvailabilityDto {
    private long id;
    @NotNull(message = "employeeId cannot be null")
    @Positive
    //ToDo must already exist in employees Table
    private Long employeeId;

//    auto from Employee table
//    private String designation;

    private String employeeAvailabilityStatus;

    @NotNull(message = "statusStartTime cannot be null")
    private LocalDateTime statusStartTime;

    private LocalDateTime statusEndTime;
}
