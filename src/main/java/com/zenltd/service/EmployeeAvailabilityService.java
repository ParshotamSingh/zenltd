package com.zenltd.service;

import com.zenltd.enums.EmployeeAvailabilityStatus;
import com.zenltd.dto.EmployeeAvailabilityDto;
import com.zenltd.entity.EmployeeAvailability;
import com.zenltd.repositories.EmployeeAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeAvailabilityService {
    @Autowired
    EmployeeAvailabilityRepository employeeAvailabilityRepository;
    //**************** for saving employee initial availability status ***************
    public void saveAvailabilityStatus(EmployeeAvailabilityDto employeeAvailabilityDto){
       EmployeeAvailability employeeAvailability = new EmployeeAvailability();
        employeeAvailability.setId(employeeAvailabilityDto.getId());
        employeeAvailability.setEmployeeId(employeeAvailabilityDto.getEmployeeId());
       employeeAvailability.setName(employeeAvailabilityDto.getName());
       employeeAvailability.setDesignation(employeeAvailabilityDto.getDesignation());
       employeeAvailability.setEmployeeAvailabilityStatus(employeeAvailabilityDto.getEmployeeAvailabilityStatus());
       employeeAvailability.setStatusStartTime(employeeAvailabilityDto.getStatusStartTime());
       employeeAvailability.setStatusEndTime(employeeAvailabilityDto.getStatusEndTime());
       employeeAvailability.setAreaOfWork(employeeAvailabilityDto.getAreaOfWork());
       employeeAvailabilityRepository.save(employeeAvailability);
   }

    //******** to update employee's scheduled availability status ***********
    public void updateEmpAvailabilityStatus(Long employeeId, EmployeeAvailabilityStatus employeeAvailabilityStatus,
                                            String startTime, String endTime){
        EmployeeAvailability employeeAvailability = employeeAvailabilityRepository.getAvailabilityByEmpId(employeeId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); //Date format changer from string to LocalDateTime
        LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);

        employeeAvailability.setEmployeeAvailabilityStatus(employeeAvailabilityStatus);
        employeeAvailability.setStatusStartTime(startDateTime);
        employeeAvailability.setStatusEndTime(endDateTime);

        employeeAvailabilityRepository.save(employeeAvailability);
    }
    //************ to get the availability status of an employee ***************
    public EmployeeAvailabilityDto getAvailabilityByEmpId(long employeeId){
        EmployeeAvailability employeeAvailability = employeeAvailabilityRepository.getAvailabilityByEmpId(employeeId);
        EmployeeAvailabilityDto employeeAvailabilityDto = new EmployeeAvailabilityDto();
        employeeAvailabilityDto.setId(employeeAvailability.getId());
        employeeAvailabilityDto.setEmployeeId(employeeAvailability.getEmployeeId());
        employeeAvailabilityDto.setName(employeeAvailability.getName());
        employeeAvailabilityDto.setDesignation(employeeAvailability.getDesignation());
        employeeAvailabilityDto.setEmployeeAvailabilityStatus(employeeAvailability.getEmployeeAvailabilityStatus());
        employeeAvailabilityDto.setAreaOfWork(employeeAvailability.getAreaOfWork());
        employeeAvailabilityDto.setStatusStartTime(employeeAvailability.getStatusStartTime());
        employeeAvailabilityDto.setStatusEndTime(employeeAvailability.getStatusEndTime());
        return employeeAvailabilityDto;
    }
    //************ to get the list of employees with particular status ****************
    public List<EmployeeAvailabilityDto> getEmployeesByStatus(EmployeeAvailabilityStatus employeeAvailabilityStatus) {
        List<EmployeeAvailabilityDto> employeeAvailabilityDtos = new ArrayList<>();
        List<EmployeeAvailability> employeeAvailabilityList = employeeAvailabilityRepository.getEmployeesByStatus(employeeAvailabilityStatus);
        for(EmployeeAvailability x: employeeAvailabilityList){
            if(x.getEmployeeAvailabilityStatus() == employeeAvailabilityStatus){
                        EmployeeAvailabilityDto employeeAvailabilityDto =getAvailabilityByEmpId(x.getEmployeeId());
                        employeeAvailabilityDtos.add(employeeAvailabilityDto);
            }
        }
        return employeeAvailabilityDtos;
    }

}
