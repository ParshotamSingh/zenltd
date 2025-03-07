package com.zenltd.entity;

import com.zenltd.enums.EmployeeAvailabilityStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
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
    @Column(name = "employee_Availability_Status")
    private String employeeAvailabilityStatus;
    @Column(name = "statusStartTime")
    private LocalDateTime statusStartTime;
    @Column(name = "statusEndTime")
    private LocalDateTime statusEndTime;
}
