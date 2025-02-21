package com.zenltd.controller;

import com.zenltd.dto.ShipmentArrivalDto;
import com.zenltd.dto.ShipmentDto;
import com.zenltd.service.ShipmentArrivalService;
import com.zenltd.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShipmentArrivalController {
    @Autowired
    ShipmentArrivalService shipmentArrivalService;
    @PostMapping(value = "/saveShipmentArrival")
    public void saveShipmentArrival(@RequestBody ShipmentArrivalDto shipmentArrivalDto){

        shipmentArrivalService.saveShipmentArrival(shipmentArrivalDto);
    }
    //*********************************************************
    @GetMapping(value = "/getShipmentArrivalByShipmentId")
    public ResponseEntity<ShipmentArrivalDto> getShipmentArrivalByShipmentId(@RequestParam long shipmentId) {
        ShipmentArrivalDto shipmentArrivalDto = shipmentArrivalService.getShipmentArrivalByShipmentId(shipmentId);
        return new ResponseEntity<>(shipmentArrivalDto, HttpStatus.OK);
    }
    //**************************************************************
}

