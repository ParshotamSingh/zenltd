package com.zenltd.controller;

import com.zenltd.enums.ShipmentInboundStatus;
import com.zenltd.dto.EmployeeAllocationDto;
import com.zenltd.service.EmployeeAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class EmployeeAllocationController {
    @Autowired
    EmployeeAllocationService employeeAllocationService;
    @PostMapping(value = "/saveEmployeeAllocation")
    public void saveEmployeeAllocation(@RequestBody EmployeeAllocationDto employeeAllocationDto){

        employeeAllocationService.saveEmployeeAllocation(employeeAllocationDto);
    }
    //**************************************************************
    @GetMapping(value = "/getAllocationsByEmpId")
    public ResponseEntity <List<EmployeeAllocationDto>> getAllocationsByEmpId(@RequestParam long empId) {
        List<EmployeeAllocationDto> employeeAllocationDtos = employeeAllocationService.getAllocationsByEmpId(empId);
        return new ResponseEntity<>(employeeAllocationDtos, HttpStatus.OK);
    }
    //**************************************************************

    @GetMapping(value = "/getAllocationsByShipmentId")
    public ResponseEntity <List<EmployeeAllocationDto>> getAllocationsByShipmentId(@RequestParam long shipmentId) {
        List<EmployeeAllocationDto> employeeAllocationDtos = employeeAllocationService.getAllocationsByShipmentId(shipmentId);
        return new ResponseEntity<>(employeeAllocationDtos, HttpStatus.OK);
    }
    //**************************************************************
    @GetMapping(value = "/getAllocationsByShipmentStatus")
    public ResponseEntity <List<EmployeeAllocationDto>> getAllocationsByShipmentStatus(@RequestParam ShipmentInboundStatus shipmentStatus) {
        List<EmployeeAllocationDto> employeeAllocationDtos = employeeAllocationService.getAllocationsByShipmentStatus(shipmentStatus);
        return new ResponseEntity<>(employeeAllocationDtos, HttpStatus.OK);
    }
    //**************************************************************
}
