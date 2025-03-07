package com.zenltd.repositories;
import com.zenltd.enums.ShipmentPreparationStatus;
import com.zenltd.entity.ShipmentPreparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShipmentPreparationRepository extends JpaRepository<ShipmentPreparation, Long>{
        @Query(value = "SELECT t FROM ShipmentPreparation t where t.shipmentId = :shipmentId")
        ShipmentPreparation getPreparationStatusByShipmentId(Long shipmentId);

        @Query(value = "SELECT t FROM ShipmentPreparation t where t.preparationStatus = :preparationStatus")
        List<ShipmentPreparation> getShipmentsByPreparationStatus(String preparationStatus);
}


