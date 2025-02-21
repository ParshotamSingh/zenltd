package com.zenltd.repositories;

import com.zenltd.entity.Shipment;
import com.zenltd.entity.ShipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Long> {
    @Query(value = "SELECT t FROM ShipmentType t where t.id = :id")
    ShipmentType getShipmentTypeById(Long id);
}
