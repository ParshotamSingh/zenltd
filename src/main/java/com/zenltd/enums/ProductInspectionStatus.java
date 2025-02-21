package com.zenltd.enums;

public enum ProductInspectionStatus {
    PASSED,       // Product is in good condition
    DAMAGED,      // Product is broken or defective
    EXPIRED,      // Product is expired
    INCORRECT,    // Product does not match shipment details
    QUARANTINE    // Product requires further checks
}
