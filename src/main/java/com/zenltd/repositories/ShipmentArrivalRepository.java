package com.zenltd.repositories;

import com.zenltd.entity.Shipment;
import com.zenltd.entity.ShipmentArrival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentArrivalRepository extends JpaRepository<ShipmentArrival, Long> {
    @Query(value = "SELECT t FROM ShipmentArrival t where t.shipmentArrivalId = :shipmentArrivalId")
    ShipmentArrival getShipmentArrivalById(Long shipmentArrivalId);

    @Query(value = "SELECT t FROM ShipmentArrival t where t.shipmentId = :shipmentId")
    ShipmentArrival getShipmentArrivalByShipmentId(Long shipmentId);

}