package com.zenltd.repositories;
import com.zenltd.entity.InboundReceiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InboundReceivingRepository extends JpaRepository<InboundReceiving, Long> {
//    @Query(value = "SELECT t FROM InboundReceiving t where t.id = :id")
//    InboundReceiving getInboundReceivingById(Long id);
//
//    @Query(value = "SELECT t FROM EmployeeAvailability t where t.shipmentId = :shipmentId")
//    List<InboundReceiving> getInboundReceivingByShipmentId(Long shipmentId);
}
