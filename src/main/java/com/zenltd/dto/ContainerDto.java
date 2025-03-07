package com.zenltd.dto;
import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Data
public class ContainerDto {
    private long id;
    @NotNull(message = "containerCode cannot be null")
    @Min(value =1, message = "containerCode can not be 0")
    private Long containerCode;
    @NotNull(message = "shipmentId cannot be null")
    @Min(value =1, message = "shipmentId can not be 0")
    private Long shipmentId;
}
