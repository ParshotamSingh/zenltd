package com.zenltd.controller;

import com.zenltd.enums.EmployeeAvailabilityStatus;
import com.zenltd.dto.EmployeeAvailabilityDto;
import com.zenltd.service.EmployeeAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeAvailabilityController {
    @Autowired
    EmployeeAvailabilityService employeeAvailabilityService;

    @PostMapping(value = "/saveAvailabilityStatus")
    public void saveAvailabilityStatus(@RequestBody EmployeeAvailabilityDto employeeAvailabilityDto) {

        employeeAvailabilityService.saveAvailabilityStatus(employeeAvailabilityDto);
    }
//***************************************************************
    @PutMapping(value = "/updateEmpAvailabilityStatus")
    public void updateEmpAvailabilityStatus(@RequestParam Long employeeId,@RequestParam EmployeeAvailabilityStatus employeeAvailabilityStatus,
                                            @RequestParam String startTime,@RequestParam String endTime) {
    employeeAvailabilityService.updateEmpAvailabilityStatus(employeeId,employeeAvailabilityStatus,startTime,endTime);
    }
//***************************************************************
    @GetMapping(value = "/getAvailabilityByEmpId")
    public ResponseEntity<EmployeeAvailabilityDto> getAvailabilityByEmpId(@RequestParam long employeeId) {
        EmployeeAvailabilityDto employeeAvailabilityDto = employeeAvailabilityService.getAvailabilityByEmpId(employeeId);
        return new ResponseEntity<>(employeeAvailabilityDto, HttpStatus.OK);
    }
//***************************************************************
@GetMapping(value = "/getEmployeesByStatus")
    public ResponseEntity<List<EmployeeAvailabilityDto>> getEmployeesByStatus(@RequestParam EmployeeAvailabilityStatus employeeAvailabilityStatus) {
        List<EmployeeAvailabilityDto> employeeAvailabilityDtos = employeeAvailabilityService.getEmployeesByStatus(employeeAvailabilityStatus);

        return new ResponseEntity<>(employeeAvailabilityDtos, HttpStatus.OK);
    }
}
