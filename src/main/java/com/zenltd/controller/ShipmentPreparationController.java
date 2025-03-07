package com.zenltd.controller;

import com.zenltd.dto.ShipmentPreparationDto;
import com.zenltd.service.ShipmentPreparationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShipmentPreparationController {
    @Autowired
    ShipmentPreparationService shipmentPreparationService;
    @Operation(summary = "Start Shipment Preparation")
    @PostMapping(value = "/saveStartPreparation")
    public void saveStartPreparation(@Valid @RequestBody ShipmentPreparationDto shipmentPreparationDto){
        shipmentPreparationService.saveStartPreparation(shipmentPreparationDto);
        }
    @Operation(summary = "Update Shipment Preparation")
    @PutMapping("/updateShipmentPreparation")
        public void updatePreparationStatus(@Valid @RequestParam long shipmentId,
                                            @Valid @RequestParam String preparationStatus,
                                            @Valid @RequestParam String remarks,
                                            @Valid @RequestParam long UpdatedByUsername) {

        shipmentPreparationService.updateShipmentPreparation(shipmentId, preparationStatus,
                                                            remarks, UpdatedByUsername);
    }
    @Operation(summary = "Cancellation of Shipment Preparation")
    @PutMapping("/cancelShipmentPreparation")
    public void cancelShipmentPreparation(@Valid @RequestParam long shipmentId,
                                            @Valid @RequestParam String remarks,
                                            @Valid @RequestParam long UpdatedByUsername){
        shipmentPreparationService.cancelShipmentPreparation(shipmentId,remarks,UpdatedByUsername);

    }
    @Operation(summary = "Completion Of Shipment Preparation")
    @PutMapping("/finaliseShipmentPreparation")
    public void finaliseShipmentPreparation(@Valid @RequestParam long shipmentId,
                                            @Valid @RequestParam String remarks,
                                            @Valid @RequestParam long UpdatedByUsername){
        shipmentPreparationService.finaliseShipmentPreparation(shipmentId,remarks,UpdatedByUsername);

    }
    @Operation(summary = "Get Preparation Status Of A Shipment")
    @GetMapping(value = "/getPreparationStatusByShipmentId")
    public ResponseEntity<ShipmentPreparationDto> getPreparationStatusByShipmentId(@Valid @RequestParam long shipmentId) {
        ShipmentPreparationDto shipmentPreparationDto = shipmentPreparationService.getPreparationStatusByShipmentId(shipmentId);
        return new ResponseEntity<>(shipmentPreparationDto, HttpStatus.OK);
    }
    @Operation(summary = "Get Shipments By Preparation Status")
    @GetMapping(value = "/getShipmentsByPreparationStatus")
    public ResponseEntity<List<ShipmentPreparationDto>> getShipmentsByPreparationStatus(@Valid @RequestParam String preparationStatus) {
        List<ShipmentPreparationDto> shipmentPreparationDtos = shipmentPreparationService.getShipmentsByPreparationStatus(preparationStatus);
        return new ResponseEntity<>(shipmentPreparationDtos, HttpStatus.OK);
    }

}

