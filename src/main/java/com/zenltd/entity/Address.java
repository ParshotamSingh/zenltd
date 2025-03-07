package com.zenltd.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "building_no")
    private int buildingNo;
    @Column(name = "street_no")
    private int streetNo;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "pin_code")
    private long pinCode;
}
