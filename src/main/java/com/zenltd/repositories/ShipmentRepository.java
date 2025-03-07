package com.zenltd.repositories;

import com.zenltd.entity.Member;
import com.zenltd.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    @Query(value = "SELECT t FROM Shipment t where t.shipmentId = :shipmentId")
    Shipment getShipmentById(Long shipmentId);
    @Query("SELECT s FROM Shipment s WHERE s.expectedDeliveryDate BETWEEN :startOfDay AND :endOfDay")
    List<Shipment> getShipmentDeliveriesForToday(LocalDateTime startOfDay,
                                                 LocalDateTime endOfDay);

}
