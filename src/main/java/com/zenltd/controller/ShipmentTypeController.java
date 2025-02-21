package com.zenltd.controller;

import com.zenltd.dto.ProductDto;
import com.zenltd.dto.ShipmentDto;
import com.zenltd.dto.ShipmentTypeDto;
import com.zenltd.entity.ShipmentType;
import com.zenltd.service.ShipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShipmentTypeController{
    @Autowired
    ShipmentTypeService shipmentTypeService;

    @PostMapping(value = "/saveShipmentType")
    public void saveShipmentType(@RequestBody ShipmentTypeDto shipmentTypeDto){
        shipmentTypeService.saveShipmentType(shipmentTypeDto);
    }
    //********************************************************************************
    @GetMapping(value = "/getShipmentTypeById")
    public ResponseEntity<ShipmentTypeDto> getShipmentTypeById(@RequestParam long id) {
        ShipmentTypeDto shipmentTypeDto   = shipmentTypeService.getShipmentTypeById(id);
        return new ResponseEntity<>(shipmentTypeDto, HttpStatus.OK);
    }
}
