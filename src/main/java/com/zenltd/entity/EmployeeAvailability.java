package com.zenltd.entity;

import com.zenltd.enums.EmployeeAvailabilityStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employeeAvailability")
public class EmployeeAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "employee_Id")
    private long employeeId;
    @Column(name = "designation")
    private String designation;
    @Column(name = "name")
    private String name;
    @Column(name = "employee_Availability_Status")
//    @Enumerated(EnumType.STRING)
    private EmployeeAvailabilityStatus employeeAvailabilityStatus;
    @Column(name = "area_Of_Work")
    private String areaOfWork;
    @Column(name = "statusStartTime")
    private LocalDateTime statusStartTime;
    @Column(name = "statusEndTime")
    private LocalDateTime statusEndTime;

    //******************************************


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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
