package com.zenltd.entity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "shipment_preparation")
public class ShipmentPreparation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long Id;
    @Column(name = "shipment_id")
    private long shipmentId;
    @Column(name = "gate_number")
    private String gateNumber;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "last_updated_time")
    private LocalDateTime lastUpdatedTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "preparation_status")
    private String preparationStatus; // "In Progress"/ "Delayed"/ "Completed"/"Cancelled"
    @Column(name = "updated_by_employee_id")
    private long updatedByUsername;
    @Column(name = "remarks")
    private String remarks;
}
