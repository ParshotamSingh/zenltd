package com.zenltd.enums;

public enum ShipmentInboundStatus {
    CREATED,            // 0 Shipment record created but not yet processed
    PENDING,            // 1 Awaiting processing or confirmation
    CONFIRMED,          // 2 Shipment confirmed by sender or logistics team
    IN_TRANSIT,         // 3 Shipment is on the way to the destination
    ARRIVED_AT_WAREHOUSE, // 4 Shipment has arrived at the receiving warehouse
    UNDER_INSPECTION,   // 5 Shipment is being checked for damages, quality, or compliance
    PARTIALLY_RECEIVED, // 6 Only part of the shipment has been received
    RECEIVED,           // 7 Shipment has been fully received
    SHORT_RECEIVED,     // 8 Some items are missing from the shipment
    DAMAGED,            // 9 Shipment or part of it is damaged
    RETURNED,           // 10 Shipment is being or has been returned to sender
    CANCELED,           // 11 Shipment was canceled before delivery
    COMPLETED,          // 12 Shipment has been fully processed and closed
    ON_HOLD,                    // 13 Shipment is temporarily put on hold for any reason (e.g., customs issues).
    AWAITING_CUSTOMS_CLEARANCE, // 14 Shipment is under customs inspection before release.
    DISPATCHED_FOR_DELIVERY ,   // 15 Shipment has left the warehouse for final delivery.
    LOST,                 // 16 Shipment is lost in transit and cannot be traced.
    IN_PROGRESS             // 17
}
