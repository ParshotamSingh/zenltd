package com.zenltd.entity;

import lombok.Data;

import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
@Data
@Entity
@Table(name = "container")
public class Container{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "container_code")
    private long containerCode;
    @Column (name = "shipment_id")
    private long shipmentId;
}
