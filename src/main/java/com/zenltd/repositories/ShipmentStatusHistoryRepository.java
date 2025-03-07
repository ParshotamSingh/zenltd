package com.zenltd.repositories;

import com.zenltd.entity.Shipment;
import com.zenltd.entity.ShipmentStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShipmentStatusHistoryRepository extends JpaRepository<ShipmentStatusHistory, Long> {
    @Query(value = "SELECT t FROM ShipmentStatusHistory t where t.shipmentId = :shipmentId")
    List<ShipmentStatusHistory> getStatusHistoryByShipmentId(Long shipmentId);

    // Fetch the latest status for a shipment by ordering timestamp in descending order
    Optional<ShipmentStatusHistory> findTopByShipmentIdOrderByStatusTimestampDesc(Long shipmentId);
    boolean existsByShipmentIdAndStatus(Long shipmentId, String status);

}
