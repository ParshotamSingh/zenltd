package com.zenltd.entity;



import java.time.Instant;
import javax.persistence.*;
import java.util.List;
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
    @Column(name ="shipment_type_id")
    private long shipmentTypeId;
    @Column(name = "source")
    private String source;
    @Column(name = "user_created_name")
    private String createdByUsername;
    @Column(name = "user_updated")
    private String userUpdated;
    @Column(name = "date_created")
    private Instant dateCreated;
    @Column(name = "date_updated")
    private Instant dateUpdated;
    @Column(name = "expected_delivery_date")
    private Instant expectedDeliveryDate;


    //****************************************************************************


    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public long getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(long shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentBarcodeId() {
        return shipmentBarcodeId;
    }

    public void setShipmentBarcodeId(String shipmentBarcodeId) {
        this.shipmentBarcodeId = shipmentBarcodeId;
    }

    public String getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }



    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    public String getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(String userUpdated) {
        this.userUpdated = userUpdated;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Instant dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Instant getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Instant expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
}
