package com.zenltd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class ShipmentArrivalDto {
    private  long shipmentArrivalId;
    @NotNull(message = "shipmentId Created cannot be null or blank")
    @Positive
    private Long shipmentId;

    @NotBlank(message = "gateNumber cannot be blank/null/empty")
    private String gateNumber;

    @NotBlank(message = "shipmentStatus cannot be blank/null/empty")
    private String shipmentStatus;

    @NotNull(message = "updatedByEmployeeId cannot be blank")
    @Positive
    private Long updatedByUsername;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;
}
