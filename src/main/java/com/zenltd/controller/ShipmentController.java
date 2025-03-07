package com.zenltd.controller;

import com.zenltd.dto.ShipmentDto;
import com.zenltd.dto.ShipmentStatusHistoryDto;
import com.zenltd.service.ShipmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ShipmentController {
    @Autowired
    ShipmentService shipmentService;
    @Operation(summary = "Save Shipment Draft")
    @PostMapping(value = "/saveShipmentDraft")
    public void saveShipmentDraft(@Valid @RequestBody ShipmentDto shipmentDto){
        shipmentService.saveShipmentDraft(shipmentDto);
    }

//    updateShipment
@Operation(summary = "Cancel Shipment")
@PutMapping(value= "/cancelShipment")
public void cancelShipment (@Valid @RequestParam Long shipmentId,
                              @Valid @RequestParam Long updatedByUsername,
                            @Valid @RequestParam String remarks){
    shipmentService.cancelShipment(shipmentId, updatedByUsername,remarks);
}
    @Operation(summary = "Confirm Shipment")
    @PutMapping(value= "/confirmShipment")
    public void confirmShipment (@Valid @RequestParam Long shipmentId,
                                @Valid @RequestParam Long updatedByUsername,
                                @Valid @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime expectedDeliveryDate,
                                  @Valid @RequestParam String remarks){
        shipmentService.confirmShipment(shipmentId, updatedByUsername,expectedDeliveryDate,remarks);
    }

    @Operation(summary = "Get Shipment By Id")
    @GetMapping(value = "/getShipmentById")
    public ResponseEntity<ShipmentDto> getShipmentById(@Valid @RequestParam long shipmentId) {
        ShipmentDto shipmentDto = shipmentService.getShipmentById(shipmentId);
        return new ResponseEntity<>(shipmentDto, HttpStatus.OK);
    }

    @Operation(summary = "Get Shipments To Be Delivered Today")
    @GetMapping(value = "/getShipmentDeliveriesForToday")
    public ResponseEntity<List<ShipmentDto>> getShipmentDeliveriesForToday() {
        List<ShipmentDto> shipmentDtos = shipmentService.getShipmentDeliveriesForToday();
        return new ResponseEntity<>(shipmentDtos, HttpStatus.OK);
    }
    @Operation(summary = "Get Status History of a Shipment")
    @GetMapping(value = "/getStatusHistoryByShipmentId")
    public ResponseEntity< List<ShipmentStatusHistoryDto>> getStatusHistoryByShipmentId(long shipmentId) {
        List<ShipmentStatusHistoryDto> ShipmentHistoryDtos = shipmentService.getStatusHistoryByShipmentId(shipmentId);
        return new ResponseEntity<>(ShipmentHistoryDtos, HttpStatus.OK);
    }
}


