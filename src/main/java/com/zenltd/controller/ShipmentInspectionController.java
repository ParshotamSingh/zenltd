package com.zenltd.controller;

import com.zenltd.enums.ShipmentInspectionStatus;
import com.zenltd.dto.ShipmentInspectionDto;
import com.zenltd.service.ShipmentInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShipmentInspectionController {
    @Autowired
    ShipmentInspectionService shipmentInspectionService;
    @PostMapping(value = "/saveStartInspection")
    public void saveStartInspection(@RequestBody ShipmentInspectionDto shipmentInspectionDto){
        shipmentInspectionService.saveStartInspection(shipmentInspectionDto);
    }
//*********************************************************
    @PutMapping("/updateInspection")
    public void updateInspection(@RequestParam long shipmentId,@RequestParam long inspectorId,
                                 @RequestParam String remarks,@RequestParam ShipmentInspectionStatus shipmentInspectionStatus){

        shipmentInspectionService.updateInspection(shipmentId, inspectorId,remarks, shipmentInspectionStatus);
    }
    //--------------------------------------------------------------------------
    @PutMapping("/completeInspection")
    public void completeInspection(@RequestParam long shipmentId,@RequestParam String remarks,
                                   @RequestParam ShipmentInspectionStatus shipmentInspectionStatus){

        shipmentInspectionService.completeInspection(shipmentId,remarks, shipmentInspectionStatus);
    }
//*********************************************************
    @GetMapping(value = "/getInspectionByShipmentId")
    public ResponseEntity<ShipmentInspectionDto> getInspectionByShipmentId(@RequestParam long shipmentId) {
        ShipmentInspectionDto shipmentInspectionDto = shipmentInspectionService.getInspectionByShipmentId(shipmentId);
        return new ResponseEntity<>(shipmentInspectionDto, HttpStatus.OK);
    }

    //...................................................................
    @GetMapping(value = "/getInspectionsByStatus")
    public ResponseEntity<List<ShipmentInspectionDto>> getInspectionsByStatus(@RequestParam ShipmentInspectionStatus shipmentInspectionStatus) {
        List<ShipmentInspectionDto> shipmentInspectionDtos = shipmentInspectionService.getInspectionsByStatus(shipmentInspectionStatus);
        return new ResponseEntity<>(shipmentInspectionDtos, HttpStatus.OK);
    }
    //...................................................................
    @GetMapping(value = "/getInspectionsByInspectorId")
    public ResponseEntity<List<ShipmentInspectionDto>> getInspectionsByInspectorId(@RequestParam long inspectorId) {
        List<ShipmentInspectionDto> shipmentInspectionDtos = shipmentInspectionService.getInspectionsByInspectorId(inspectorId);
        return new ResponseEntity<>(shipmentInspectionDtos, HttpStatus.OK);
    }
}
