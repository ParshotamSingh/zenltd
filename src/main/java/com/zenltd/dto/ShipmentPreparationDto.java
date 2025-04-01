    package com.zenltd.dto;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import lombok.Data;

    import javax.persistence.Column;
    import javax.validation.constraints.*;
    import java.time.LocalDateTime;
    @Data
    public class ShipmentPreparationDto {
        private  long Id;

        @NotNull(message = "shipmentId cannot be null")
        @Positive
        //must already exist in shipmentDetails Table
        private Long shipmentId;

        @NotBlank(message = "gateNumber cannot be null/blank/empty")
        private String gateNumber;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lastUpdatedTime;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        @NotBlank(message = "Preparation Status cannot be null/blank/empty")
        private String preparationStatus;

        @NotNull(message = "updatedByUsername cannot be null")
        @Positive
        private Long updatedByUsername;

        @Size(max = 100, message = "remarks should not exceed 100 characters")
        private String remarks; // Any additional comments
    }
