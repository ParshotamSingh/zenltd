package com.zenltd.repositories;
import com.zenltd.enums.ShipmentInboundStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.zenltd.entity.EmployeeAllocation;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeAllocationRepository extends JpaRepository<EmployeeAllocation, Long> {
    @Query(value = "SELECT t FROM EmployeeAllocation t where t.empId = :empId")
    List<EmployeeAllocation> getAllocationsByEmpId(Long empId);
    @Query(value = "SELECT t FROM EmployeeAllocation t where t.shipmentId = :shipmentId")
    List<EmployeeAllocation> getAllocationsByShipmentId(Long shipmentId);

    @Query(value = "SELECT t FROM EmployeeAllocation t where t.shipmentStatus = :shipmentStatus")
    List<EmployeeAllocation> getAllocationsByShipmentStatus(ShipmentInboundStatus shipmentStatus);
}
