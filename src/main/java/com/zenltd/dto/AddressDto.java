package com.zenltd.dto;

import lombok.Data;

@Data
public class AddressDto {
    private long id;
    private int buildingNo;
    private int streetNo;
    private String city;
    private String state;
    private int pinCode;
}
