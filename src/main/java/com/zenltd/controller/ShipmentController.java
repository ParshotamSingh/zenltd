package com.zenltd.controller;

import com.zenltd.dto.ShipmentDto;
import com.zenltd.entity.Container;
import com.zenltd.entity.Shipment;
import com.zenltd.service.ContainerService;
import com.zenltd.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShipmentController {
    @Autowired
    ShipmentService shipmentService;

    @PostMapping(value = "/saveShipment")
    public void saveShipment(@RequestBody ShipmentDto shipmentDto){

        shipmentService.saveShipment(shipmentDto);
    }
    //*********************************************************
    @GetMapping(value = "/getShipmentById")
    public ResponseEntity<ShipmentDto> getShipmentById(@RequestParam long shipmentId) {
        ShipmentDto shipmentDto = shipmentService.getShipmentById(shipmentId);
        return new ResponseEntity<>(shipmentDto, HttpStatus.OK);
    }
    //**************************************************************

}


