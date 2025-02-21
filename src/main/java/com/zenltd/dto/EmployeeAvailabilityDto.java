package com.zenltd.dto;

import com.zenltd.enums.EmployeeAvailabilityStatus;

import java.time.LocalDateTime;

public class EmployeeAvailabilityDto {
    private long id;
    private long employeeId;
    private String designation;
    private String name;
    private EmployeeAvailabilityStatus employeeAvailabilityStatus;
    private String areaOfWork;
    private LocalDateTime statusStartTime;
    private LocalDateTime statusEndTime;
    //******************************************


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStatusStartTime() {
        return statusStartTime;
    }

    public void setStatusStartTime(LocalDateTime statusStartTime) {
        this.statusStartTime = statusStartTime;
    }

    public LocalDateTime getStatusEndTime() {
        return statusEndTime;
    }

    public void setStatusEndTime(LocalDateTime statusEndTime) {
        this.statusEndTime = statusEndTime;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeAvailabilityStatus getEmployeeAvailabilityStatus() {
        return employeeAvailabilityStatus;
    }

    public void setEmployeeAvailabilityStatus(EmployeeAvailabilityStatus employeeAvailabilityStatus) {
        this.employeeAvailabilityStatus = employeeAvailabilityStatus;
    }

    public String getAreaOfWork() {
        return areaOfWork;
    }

    public void setAreaOfWork(String areaOfWork) {
        this.areaOfWork = areaOfWork;
    }
}
