package com.zenltd.enums;

public enum ShipmentPreparationStatus {
    NOT_STARTED,           // 01 Preparation not started
    IN_PROGRESS,           // 02 Currently being prepared
    AWAITING_INSPECTION,   // 03 Waiting for inspection
    INSPECTION_IN_PROGRESS,// 04 Inspection is ongoing
    READY_FOR_RECEIVING,   // 05 Ready for inbound receiving
    COMPLETED,             // 06 Shipment preparation completed
    DELAYED,               // 07 Delayed due to issues
    CANCELLED              // 08 Shipment preparation cancelled
}
