package com.zenltd.entity;

import com.zenltd.enums.ProductInboundStatus;
import com.zenltd.enums.ProductInspectionStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "inbound_receiving")
public class InboundReceiving {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "product_code")
    private long productCode;
    @Column(name = "shipment_id")
    private long shipmentId;
    @Column(name = "inspector_id")
    private long inspectorId;
    @Column(name = "product_inspection_status")
    private ProductInspectionStatus productInspectionStatus;
    @Column(name = "product_inbound_status")
    private ProductInboundStatus productInboundStatus;
    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;
}
