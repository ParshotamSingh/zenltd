package com.zenltd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.bytebuddy.asm.Advice;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class ShipmentDto {
    private  Long shipmentId;

    @NotNull(message = "memberId cannot be null")
    @Positive
    private Long memberId;

    @NotBlank(message = "shipmentBarcodeId cannot be blank")
    private String shipmentBarcodeId;

    @NotBlank(message = "businessUnitId cannot be blank")
    private String businessUnitId;

    @NotBlank(message = "referenceNumber cannot be blank")
    private String referenceNumber;

    @NotBlank(message = "transportType cannot be blank")
    private String transportType;

    @NotBlank(message = "shipmentType cannot be blank")
    private String shipmentType;

    @NotBlank(message = "source cannot be blank")
    private String source;

    @NotNull(message = "createdByUsername cannot be null")
    //ToDo must be present in employee table
    private Long createdByUsername;

    //ToDo must be present in employee table
    private Long updatedByUsername;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateUpdated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expectedDeliveryDate;

    private String currentStatus;
    @Size(max = 100, message = "remarks should not exceed 100 characters")
    private String remarks;
}
