package com.zenltd.controller;

import com.zenltd.dto.ContainerDto;
import com.zenltd.service.ContainerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContainerController {
    @Autowired
    ContainerService containerService;
    @Operation(summary = "Save Container")
    @PostMapping("/saveContainer")
    public void saveContainer(@Valid @RequestBody ContainerDto containerDto) {
        containerService.saveContainer(containerDto);
    }
    @Operation(summary = "Get Container By Id")
    @GetMapping(value = "/getContainerById")
    public ResponseEntity<ContainerDto> getContainerById(@Valid @RequestParam long id) {
        ContainerDto containerDto = containerService.getContainerById(id);
        return new ResponseEntity<>(containerDto, HttpStatus.OK);
    }
    @Operation(summary = "Get List of Containers By Shipment Id")
    @GetMapping(value ="/getAllContainersByShipmentId")
    public ResponseEntity<List<ContainerDto>> getAllContainersByShipmentId(@Valid @RequestParam long shipmentId){
        List<ContainerDto> listOfContainerDto = containerService.getAllContainersByShipmentId(shipmentId);
        return new ResponseEntity<>(listOfContainerDto, HttpStatus.OK);
    }
}






