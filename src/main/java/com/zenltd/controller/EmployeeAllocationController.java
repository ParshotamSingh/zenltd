package com.zenltd.controller;

import com.zenltd.enums.ShipmentInboundStatus;
import com.zenltd.dto.EmployeeAllocationDto;
import com.zenltd.service.EmployeeAllocationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class EmployeeAllocationController {
    @Autowired
    EmployeeAllocationService employeeAllocationService;
    @Operation(summary = "Save Employee Allocation")
    @PostMapping(value = "/saveEmployeeAllocation")
    public void saveEmployeeAllocation(@Valid @RequestBody EmployeeAllocationDto employeeAllocationDto){

        employeeAllocationService.saveEmployeeAllocation(employeeAllocationDto);
    }
    @Operation(summary = "Get All Allocations Of An Employee")
    @GetMapping(value = "/getAllocationsByEmpId")
    public ResponseEntity <List<EmployeeAllocationDto>> getAllocationsByEmpId(@Valid @RequestParam long empId) {
        List<EmployeeAllocationDto> employeeAllocationDtos = employeeAllocationService.getAllocationsByEmpId(empId);
        return new ResponseEntity<>(employeeAllocationDtos, HttpStatus.OK);
    }
    @Operation(summary = "Get List of Employees Allocated To A Shipment ")
    @GetMapping(value = "/getAllocationsByShipmentId")
    public ResponseEntity <List<EmployeeAllocationDto>> getAllocationsByShipmentId(@Valid @RequestParam long shipmentId) {
        List<EmployeeAllocationDto> employeeAllocationDtos = employeeAllocationService.getAllocationsByShipmentId(shipmentId);
        return new ResponseEntity<>(employeeAllocationDtos, HttpStatus.OK);
    }
    @Operation(summary = "Get Allocations By Shipment Status")
    @GetMapping(value = "/getAllocationsByShipmentStatus")
    public ResponseEntity <List<EmployeeAllocationDto>> getAllocationsByShipmentStatus(@Valid @RequestParam ShipmentInboundStatus shipmentStatus) {
        List<EmployeeAllocationDto> employeeAllocationDtos = employeeAllocationService.getAllocationsByShipmentStatus(shipmentStatus);
        return new ResponseEntity<>(employeeAllocationDtos, HttpStatus.OK);
    }
}
