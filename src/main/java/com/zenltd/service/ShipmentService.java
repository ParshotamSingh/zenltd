package com.zenltd.service;

import com.zenltd.dto.ShipmentDto;
import com.zenltd.entity.Shipment;
import com.zenltd.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ShipmentService {
        @Autowired
        ShipmentRepository shipmentRepository;

        public void saveShipment(ShipmentDto shipmentDto) {
                Shipment shipment = new Shipment();
                shipment.setShipmentId(shipmentDto.getShipmentId());
                shipment.setMemberId(shipmentDto.getMemberId());
                shipment.setShipmentBarcodeId(shipmentDto.getShipmentBarcodeId());
                shipment.setBusinessUnitId(shipmentDto.getBusinessUnitId());
                shipment.setReferenceNumber(shipmentDto.getReferenceNumber());
                shipment.setTransportType(shipmentDto.getTransportType());
                shipment.setShipmentTypeId(shipmentDto.getShipmentTypeId());
                shipment.setSource(shipmentDto.getSource());
                shipment.setCreatedByUsername(shipmentDto.getCreatedByUsername());
                shipment.setUserUpdated(shipmentDto.getUserUpdated());
                shipment.setDateCreated(Instant.now());
                shipment.setDateUpdated(Instant.now());
                shipment.setExpectedDeliveryDate(shipmentDto.getExpectedDeliveryDate());

                shipmentRepository.save(shipment);
        }
//******************************************************************
        public ShipmentDto getShipmentById(long shipmentId) {
                Shipment shipment = shipmentRepository.getShipmentById(shipmentId);
                ShipmentDto shipmentDto = new ShipmentDto();
                shipmentDto.setShipmentId(shipment.getShipmentId());
                shipmentDto.setMemberId(shipment.getMemberId());
                shipmentDto.setShipmentBarcodeId(shipment.getShipmentBarcodeId());
                shipmentDto.setBusinessUnitId(shipment.getBusinessUnitId());
                shipmentDto.setReferenceNumber(shipment.getReferenceNumber());
                shipmentDto.setTransportType(shipment.getTransportType());
                shipmentDto.setShipmentTypeId(shipment.getShipmentTypeId());
                shipmentDto.setSource(shipment.getSource());
                shipmentDto.setCreatedByUsername(shipment.getCreatedByUsername());
                shipmentDto.setUserUpdated(shipment.getUserUpdated());
                shipmentDto.setDateCreated(shipment.getDateCreated());
                shipmentDto.setDateUpdated(shipment.getDateUpdated());
                shipmentDto.setExpectedDeliveryDate(shipment.getExpectedDeliveryDate());
                return shipmentDto;
        }
}
