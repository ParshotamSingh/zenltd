package com.zenltd.enums;

public enum ProductInboundStatus {

    RECEIVED,     // Product successfully received
    REJECTED,     // Product failed inspection
    HOLD,         // Product is under review (Quarantine)
    RETURNED      // Product is sent back to supplier
 }
