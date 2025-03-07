package com.zenltd.dto;

import com.zenltd.entity.ProductAttribute;
import lombok.Data;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    private long id;
    @NotNull(message = "productCode cannot be null")
    @Min(value =1, message = "productCode can not be 0")
    //@Unique
    private Long productCode;
    @NotNull(message = "containerId cannot be null")
    @Min(value =1, message = "containerId can not be 0")
    private Long containerId;

    @Min(value =1, message = "shipmentId can not be 0")
    private Long shipmentId;

    @NotNull(message = "productAttributeDto cannot be null")
    @Valid
    private ProductAttributeDto productAttributeDto;

}
