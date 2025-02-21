package com.zenltd.repositories;

import com.zenltd.enums.EmployeeAvailabilityStatus;
import com.zenltd.entity.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeAvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {
    @Query(value = "SELECT t FROM EmployeeAvailability t where t.employeeId = :employeeId")
    EmployeeAvailability getAvailabilityByEmpId(Long employeeId);

    @Query(value = "SELECT t FROM EmployeeAvailability t where t.employeeAvailabilityStatus = :employeeAvailabilityStatus")
    List<EmployeeAvailability> getEmployeesByStatus(EmployeeAvailabilityStatus employeeAvailabilityStatus);
}
