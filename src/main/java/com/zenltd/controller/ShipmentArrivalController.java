package com.zenltd.controller;

import com.zenltd.dto.ShipmentArrivalDto;
import com.zenltd.dto.ShipmentDto;
import com.zenltd.service.ShipmentArrivalService;
import com.zenltd.service.ShipmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ShipmentArrivalController {
    @Autowired
    ShipmentArrivalService shipmentArrivalService;
    @Operation(summary = "Save Shipment Arrival")
    @PostMapping(value = "/saveShipmentArrival")
    public void saveShipmentArrival(@Valid @RequestBody ShipmentArrivalDto shipmentArrivalDto){

        shipmentArrivalService.saveShipmentArrival(shipmentArrivalDto);
    }
    @Operation(summary = "Get Shipment Arrival Status Of A Shipment")
    @GetMapping(value = "/getShipmentArrivalByShipmentId")
    public ResponseEntity<ShipmentArrivalDto> getShipmentArrivalByShipmentId(@Valid @RequestParam long shipmentId) {
        ShipmentArrivalDto shipmentArrivalDto = shipmentArrivalService.getShipmentArrivalByShipmentId(shipmentId);
        return new ResponseEntity<>(shipmentArrivalDto, HttpStatus.OK);
    }
}

