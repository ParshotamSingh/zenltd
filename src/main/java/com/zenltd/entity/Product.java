package com.zenltd.entity;


import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "product_code")
    private long productCode;
    @Column(name = "container_id")
    private long containerId;
    @Column(name = "shipment_id")
    private long shipmentId;
    @Column (name = "product_attribute_id")
    private long productAttributeId;
}
