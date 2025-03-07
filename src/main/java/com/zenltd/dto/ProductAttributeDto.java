package com.zenltd.dto;

import lombok.Data;
import javax.validation.constraints.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class ProductAttributeDto {
    private long id;
    @NotBlank(message = "productName cannot be blank")
    @NotNull(message = "productName cannot be null")
    private String productName;
    @NotBlank(message = "productType cannot be blank")
    private String productType;
    @NotBlank(message = "productState cannot be blank")
    private String productState;
    @NotBlank(message = "productionState cannot be blank")
    private String productionState;
    @NotBlank(message = "temperatureRequirement cannot be blank")
    private String temperatureRequirement;
    @NotNull(message = "shelfLife cannot be null")
    private Integer shelfLife;
    @NotBlank(message = "breakability cannot be blank")
    private String breakability;
    @NotBlank(message = "flammability cannot be blank")
    private String flammability;
    @NotBlank(message = "explosibility cannot be blank")
    private String explosibility;
    @NotBlank(message = "perishability cannot be blank")
    private String perishability;
    @NotBlank(message = "packagingType cannot be blank")
    private String packagingType;
    @NotBlank(message = "material cannot be blank")
    private String material;
    @NotNull(message = "noOfUnits cannot be null")
    @Min(value =1, message = "noOfUnits can not be 0")
    private Integer noOfUnits;
    @NotBlank(message = "manufacturerDetails cannot be blank")
    private String manufacturerDetails;
    @NotBlank(message = "countryOfOrigin cannot be blank")
    private String countryOfOrigin;
    @NotBlank(message = "marketedBy cannot be blank")
    private String marketedBy;
    @NotBlank(message = "seller cannot be blank")
    private String seller;
}
