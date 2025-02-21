package com.zenltd.controller;

import com.zenltd.dto.ContainerDto;
import com.zenltd.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContainerController {
    @Autowired
    ContainerService containerService;
    @PostMapping("/saveContainer")
    public void saveContainer(@RequestBody ContainerDto containerDto) {
        containerService.saveContainer(containerDto);
    }
//*****************************************************************
    @GetMapping(value = "/getContainerById")
    public ResponseEntity<ContainerDto> getContainerById(@RequestParam long id) {
        ContainerDto containerDto = containerService.getContainerById(id);
        return new ResponseEntity<>(containerDto, HttpStatus.OK);
    }
//*****************************************************************
    @GetMapping(value ="/getAllContainersByShipmentId")
    public ResponseEntity<List<ContainerDto>> getAllContainersByShipmentId(@RequestParam long shipmentId){
        List<ContainerDto> listOfContainerDto = containerService.getAllContainersByShipmentId(shipmentId);
        return new ResponseEntity<>(listOfContainerDto, HttpStatus.OK);
    }
//    @GetMapping(value ="/getContainerByShipmentId")
//    public ResponseEntity<ContainerDto> getContainerByShipmentId(@RequestParam long shipmentId){
//            ContainerDto containerDto = containerService.getContainerByShipmentId(shipmentId);
//        return new ResponseEntity<>(containerDto, HttpStatus.OK);
//    }

}






