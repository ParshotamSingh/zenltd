package com.zenltd.controller;

import com.zenltd.dto.InboundReceivingDto;
import com.zenltd.dto.MemberDto;
import com.zenltd.entity.InboundReceiving;
import com.zenltd.entity.Product;
import com.zenltd.service.InboundReceivingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@RestController
public class InboundReceivingController {
    @Autowired
    InboundReceivingService inboundReceivingService;
    @Operation(summary = "Save Inbound Receiving Status")
    @PostMapping(value = "/saveInboundService")
    public void saveInboundService(@Valid @RequestBody InboundReceivingDto inboundReceivingDto){
        inboundReceivingService.saveInboundService(inboundReceivingDto);
    }
    @Operation(summary = "Get List of Products Not Yet Inbound Received")
    @GetMapping(value = "/getProductsNotYetInboundReceived")
    public ResponseEntity<List<Product>> getProductsNotYetInboundReceived() {
        List<Product> listOfMissingProducts = inboundReceivingService.getProductsNotYetInboundReceived();
        return new ResponseEntity<>(listOfMissingProducts, HttpStatus.OK);
    }
    @Operation(summary = "Update Shipment Status If All Products Received")
    @PutMapping(value = "/updateShipmentStatusIfAllProductsReceived")
    public void updateShipmentStatusIfAllProductsReceived(@Valid @RequestParam Long shipmentId,
                                                          @Valid @RequestParam Long updatedByUsername) {
        inboundReceivingService.updateShipmentStatusIfAllProductsReceived(shipmentId,updatedByUsername);
    }
    @Operation(summary = "Get List of Uninspected Products Of A Shipment")
    @GetMapping(value = "/getProductsNotYetInspectedInShipment")
    public ResponseEntity<List<Product>> getProductsNotYetInspectedInShipment(@Valid @RequestParam Long shipmentId) {
        List<Product> listOfUninspectedProductsInShipment = inboundReceivingService.getProductsNotYetInspectedInShipment(shipmentId);
        return new ResponseEntity<>(listOfUninspectedProductsInShipment, HttpStatus.OK);
    }
}
