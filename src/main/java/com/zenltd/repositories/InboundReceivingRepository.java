package com.zenltd.repositories;
import com.zenltd.entity.InboundReceiving;
import com.zenltd.enums.ProductInboundStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InboundReceivingRepository extends JpaRepository<InboundReceiving, Long> {
    @Query(value = "SELECT t FROM InboundReceiving t where t.id = :id")
    InboundReceiving getInboundReceivingById(Long id);
    @Query(value = "SELECT t FROM InboundReceiving t where t.productCode = :productCode")
    InboundReceiving getInboundReceivingByProductCode(Long productCode);
    @Query(value = "SELECT t FROM InboundReceiving t where t.shipmentId = :shipmentId")
    List<InboundReceiving> getInboundReceivingByShipmentId(Long shipmentId);
    @Query("SELECT t FROM InboundReceiving t WHERE t.shipmentId = :shipmentId AND t.productInboundStatus = :productInboundStatus")
    List<InboundReceiving> findByShipmentIdAndProductInboundStatus(Long shipmentId, ProductInboundStatus productInboundStatus);
}
