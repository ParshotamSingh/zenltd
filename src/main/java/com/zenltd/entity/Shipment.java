package com.zenltd.entity;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import javax.persistence.*;

@Data
@Entity
@Table(name = "shipment_details")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long shipmentId;
    @Column(name = "member_id")
    private long memberId;
    @Column(name = "shipment_barcode_id")
    private String shipmentBarcodeId;
    @Column(name = "business_unit_id")
    private String businessUnitId;
    @Column(name = "reference_number")
    private String referenceNumber;
    @Column(name = "transport_type")
    private String transportType;
    @Column(name ="shipment_type")
    private String shipmentType;
    @Column(name = "source")
    private String source;
    @Column(name = "created_by_username")
    private long createdByUsername;
    @Column(name = "updated_by_username")
    private long updatedByUsername;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
    @Column(name = "expected_delivery_date")
    private LocalDateTime expectedDeliveryDate;
    @Column(name = "current_status")
    private String currentStatus;
    @Column (name="remarks")
    private String remarks;
}
