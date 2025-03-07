package com.zenltd.controller;

import com.zenltd.enums.EmployeeAvailabilityStatus;
import com.zenltd.dto.EmployeeAvailabilityDto;
import com.zenltd.service.EmployeeAvailabilityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeAvailabilityController {
    @Autowired
    EmployeeAvailabilityService employeeAvailabilityService;
    @Operation(summary = "Save Employee Availability Status")
    @PostMapping(value = "/saveAvailabilityStatus")
    public void saveAvailabilityStatus(@Valid @RequestBody EmployeeAvailabilityDto employeeAvailabilityDto) {

        employeeAvailabilityService.saveAvailabilityStatus(employeeAvailabilityDto);
    }
    @Operation(summary = "Update Employee Availability Status")
    @PutMapping(value = "/updateEmpAvailabilityStatus")
    public void updateEmpAvailabilityStatus(@Valid @RequestParam Long employeeId,@Valid @RequestParam String employeeAvailabilityStatus,
                                            @Valid @RequestParam String startTime,@Valid @RequestParam String endTime) {
    employeeAvailabilityService.updateEmpAvailabilityStatus(employeeId,employeeAvailabilityStatus,startTime,endTime);
    }
    @Operation(summary = "Get Availability Status Of an Employee")
    @GetMapping(value = "/getAvailabilityByEmpId")
    public ResponseEntity<EmployeeAvailabilityDto> getAvailabilityByEmpId(@Valid @RequestParam long employeeId) {
        EmployeeAvailabilityDto employeeAvailabilityDto = employeeAvailabilityService.getAvailabilityByEmpId(employeeId);
        return new ResponseEntity<>(employeeAvailabilityDto, HttpStatus.OK);
    }
    @Operation(summary = "Get List of Employees with a Particular Availability Status")
    @GetMapping(value = "/getEmployeesByStatus")
    public ResponseEntity<List<EmployeeAvailabilityDto>> getEmployeesByStatus(@Valid @RequestParam String employeeAvailabilityStatus) {
        List<EmployeeAvailabilityDto> employeeAvailabilityDtos = employeeAvailabilityService.getEmployeesByStatus(employeeAvailabilityStatus);

        return new ResponseEntity<>(employeeAvailabilityDtos, HttpStatus.OK);
    }
}
