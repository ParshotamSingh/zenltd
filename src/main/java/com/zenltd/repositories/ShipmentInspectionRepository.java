package com.zenltd.repositories;

import com.zenltd.enums.ShipmentInspectionStatus;
import com.zenltd.entity.ShipmentInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentInspectionRepository extends JpaRepository<ShipmentInspection, Long> {
    @Query(value = "SELECT t FROM ShipmentInspection t where t.shipmentId = :shipmentId")
    ShipmentInspection getInspectionByShipmentId(Long shipmentId);

    @Query(value = "SELECT t FROM ShipmentInspection t where t.shipmentInspectionStatus = :shipmentInspectionStatus")
    List<ShipmentInspection> getInspectionsByStatus(ShipmentInspectionStatus shipmentInspectionStatus);

    @Query(value = "SELECT t FROM ShipmentInspection t where t.inspectorId = :inspectorId")
    List<ShipmentInspection> getInspectionsByInspectorId(long inspectorId);


}
