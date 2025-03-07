package com.zenltd.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shipment_arrival")
public class ShipmentArrival {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long shipmentArrivalId;
    @Column(name = "shipment_id")
    private long shipmentId;
    @Column(name = "gate_number")
    private String gateNumber;
    @Column(name = "shipment_status")
    private String shipmentStatus;
    @Column(name = "updated_by_employee_id")
    private long updatedByUsername;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
}
