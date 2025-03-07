package com.zenltd.entity;

import com.zenltd.enums.ShipmentInboundStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "employee_allocation")
public class EmployeeAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "shipment_id")
    private long shipmentId;
    @Column(name = "emp_id")
    private long empId;
    @Column(name = "shipment_status")
    private ShipmentInboundStatus shipmentStatus;
    @Column(name = "createdBy")
    private long createdBy;
    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;
}

