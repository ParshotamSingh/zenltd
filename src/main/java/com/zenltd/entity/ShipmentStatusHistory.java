package com.zenltd.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shipment_status_history")
public class ShipmentStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column (name = "shipment_id")
    private long shipmentId;
    @Column (name = "status")
    private String status;
    @Column (name = "status_timestamp")
    private LocalDateTime statusTimestamp;
    @Column (name = "updated_by_username")
    private long updatedByUsername;

}
