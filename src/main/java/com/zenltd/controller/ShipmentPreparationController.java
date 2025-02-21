package com.zenltd.controller;

import com.zenltd.enums.ShipmentPreparationStatus;
import com.zenltd.dto.ShipmentPreparationDto;
import com.zenltd.service.ShipmentPreparationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShipmentPreparationController {
    @Autowired
    ShipmentPreparationService shipmentPreparationService;
    @PostMapping(value = "/saveStartPreparation")
    public void saveStartPreparation(@RequestBody ShipmentPreparationDto shipmentPreparationDto){
        shipmentPreparationService.saveStartPreparation(shipmentPreparationDto);
        }
//        //*********************************************************
    @PutMapping("/updateShipmentPreparation")
        public void updatePreparationStatus(@RequestParam long shipmentId, @RequestParam ShipmentPreparationStatus status,
                                            @RequestParam String remarks) {

        shipmentPreparationService.updateShipmentPreparation(shipmentId, status, remarks);
    }
    //*********************************************************
    @PutMapping("/completeShipmentPreparation")
    public void completeShipmentPreparation(@RequestParam long shipmentId, @RequestParam String remarks){
        shipmentPreparationService.completeShipmentPreparation(shipmentId, remarks);

    }

    //...................................................................
    @GetMapping(value = "/getPreparationStatusByShipmentId")
    public ResponseEntity<ShipmentPreparationDto> getPreparationStatusByShipmentId(@RequestParam long shipmentId) {
        ShipmentPreparationDto shipmentPreparationDto = shipmentPreparationService.getPreparationStatusByShipmentId(shipmentId);
        return new ResponseEntity<>(shipmentPreparationDto, HttpStatus.OK);
    }
}

