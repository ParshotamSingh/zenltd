package com.zenltd.dto;

import com.zenltd.enums.ShipmentInboundStatus;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
@Data
public class EmployeeAllocationDto {
    private long id;

    @NotNull(message = "shipmentId cannot be null")
    @Min(value =1, message = "shipmentId can not be 0")
    //must already exist in shipmentDetails Table
    private Long shipmentId;

    @NotNull(message = "empId cannot be null")
    @Min(value =1, message = "empId can not be 0")
    //must already exist in employees Table
    private Long empId;

    private ShipmentInboundStatus shipmentStatus;

    @NotNull(message = "createdBy cannot be null")
    @Min(value =1, message = "createdBy can not be 0")
    //must already exist in employees Table
    private Long createdBy;

    @NotNull(message = "startTime cannot be null")
    private LocalDateTime startDateTime;

    @NotNull(message = "End time cannot be null")
//    @Past(message = "End time must be in the past")
    private LocalDateTime endDateTime;
}
