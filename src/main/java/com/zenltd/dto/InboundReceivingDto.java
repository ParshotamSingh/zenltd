package com.zenltd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zenltd.enums.ProductInboundStatus;
import com.zenltd.enums.ProductInspectionStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
@Data
public class InboundReceivingDto {
    private long id;

    @NotNull(message = "productCode cannot be null")
    @Positive
    //ToDo must already exist in product Table
    private Long productCode;

    private Long shipmentId;

    @NotNull(message = "inspectorId cannot be null")
    @Positive
    //ToDo must already exist in Employee Table
    //ToDo should be available in availability table
    private Long inspectorId;

    @NotNull(message = "productInspectionStatus cannot be null/blank/empty")
    @Enumerated(EnumType.STRING) // Store as String instead of Ordinal
    private ProductInspectionStatus productInspectionStatus;

    @Enumerated(EnumType.STRING) // Store as String instead of Ordinal
    private ProductInboundStatus productInboundStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;
}
