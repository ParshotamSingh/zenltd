package com.zenltd.service;
import com.zenltd.enums.ShipmentInboundStatus;
import com.zenltd.dto.EmployeeAllocationDto;
import com.zenltd.entity.EmployeeAllocation;
import com.zenltd.repositories.EmployeeAllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeAllocationService{
    @Autowired
    EmployeeAllocationRepository employeeAllocationRepository;

    public void saveEmployeeAllocation(EmployeeAllocationDto employeeAllocationDto){
        EmployeeAllocation employeeAllocation = new EmployeeAllocation();
        employeeAllocation.setShipmentId(employeeAllocationDto.getShipmentId());
        employeeAllocation.setEmpId(employeeAllocationDto.getEmpId());
        employeeAllocation.setCreatedBy(employeeAllocationDto.getCreatedBy());
        employeeAllocation.setShipmentStatus(employeeAllocationDto.getShipmentStatus());
        employeeAllocation.setStartDateTime(employeeAllocationDto.getStartDateTime());
        employeeAllocation.setEndDateTime(employeeAllocationDto.getEndDateTime());
        employeeAllocationRepository.save(employeeAllocation);
    }

    public List<EmployeeAllocationDto> getAllocationsByEmpId(long empId){
        List<EmployeeAllocationDto> employeeAllocationDtos = new ArrayList<>();
        List<EmployeeAllocation> employeeAllocationList = employeeAllocationRepository.getAllocationsByEmpId(empId);
        for(EmployeeAllocation x : employeeAllocationList){
            EmployeeAllocationDto employeeAllocationDto = new EmployeeAllocationDto();
            employeeAllocationDto.setId(x.getId());
            employeeAllocationDto.setEmpId(x.getEmpId());
            employeeAllocationDto.setShipmentId(x.getShipmentId());
            employeeAllocationDto.setShipmentStatus(x.getShipmentStatus());
            employeeAllocationDto.setCreatedBy(x.getCreatedBy());
            employeeAllocationDto.setStartDateTime(x.getStartDateTime());
            employeeAllocationDto.setEndDateTime(x.getEndDateTime());
            employeeAllocationDtos.add(employeeAllocationDto);
        }
        return employeeAllocationDtos;
    }
    public List<EmployeeAllocationDto> getAllocationsByShipmentId(long shipmentId){
        List<EmployeeAllocationDto> employeeAllocationDtos = new ArrayList<>();
        List<EmployeeAllocation> employeeAllocationList = employeeAllocationRepository.getAllocationsByShipmentId(shipmentId);
        for(EmployeeAllocation x : employeeAllocationList){
            EmployeeAllocationDto employeeAllocationDto = new EmployeeAllocationDto();
            employeeAllocationDto.setId(x.getId());
            employeeAllocationDto.setEmpId(x.getEmpId());
            employeeAllocationDto.setShipmentId(x.getShipmentId());
            employeeAllocationDto.setShipmentStatus(x.getShipmentStatus());
            employeeAllocationDto.setCreatedBy(x.getCreatedBy());
            employeeAllocationDto.setStartDateTime(x.getStartDateTime());
            employeeAllocationDto.setEndDateTime(x.getEndDateTime());
            employeeAllocationDtos.add(employeeAllocationDto);
        }
        return employeeAllocationDtos;
    }
    public List<EmployeeAllocationDto> getAllocationsByShipmentStatus(ShipmentInboundStatus shipmentStatus){
        List<EmployeeAllocationDto> employeeAllocationDtos = new ArrayList<>();
        List<EmployeeAllocation> employeeAllocationList = employeeAllocationRepository.getAllocationsByShipmentStatus(shipmentStatus);
        for(EmployeeAllocation x : employeeAllocationList){
            EmployeeAllocationDto employeeAllocationDto = new EmployeeAllocationDto();
            employeeAllocationDto.setId(x.getId());
            employeeAllocationDto.setEmpId(x.getEmpId());
            employeeAllocationDto.setShipmentId(x.getShipmentId());
            employeeAllocationDto.setShipmentStatus(x.getShipmentStatus());
            employeeAllocationDto.setCreatedBy(x.getCreatedBy());
            employeeAllocationDto.setStartDateTime(x.getStartDateTime());
            employeeAllocationDto.setEndDateTime(x.getEndDateTime());

            employeeAllocationDtos.add(employeeAllocationDto);
        }
        return employeeAllocationDtos;

    }
}
