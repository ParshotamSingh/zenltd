package com.zenltd.service;

import com.zenltd.dto.ShipmentArrivalDto;
import com.zenltd.entity.Shipment;
import com.zenltd.entity.ShipmentArrival;
import com.zenltd.repositories.ShipmentArrivalRepository;
import com.zenltd.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ShipmentArrivalService {
    @Autowired
    ShipmentArrivalRepository shipmentArrivalRepository;
    @Autowired
    ShipmentRepository shipmentRepository;
    public void saveShipmentArrival(ShipmentArrivalDto shipmentArrivalDto){
        ShipmentArrival shipmentArrival = new ShipmentArrival();
        shipmentArrival.setShipmentArrivalId(shipmentArrivalDto.getShipmentArrivalId());
        shipmentArrival.setShipmentId(shipmentArrivalDto.getShipmentId());
        shipmentArrival.setGateNumber(shipmentArrivalDto.getGateNumber());
        shipmentArrival.setShipmentStatus(shipmentArrivalDto.getShipmentStatus());
        shipmentArrival.setUpdatedByEmployeeId(shipmentArrivalDto.getUpdatedByEmployeeId());
        shipmentArrival.setDateCreated(Instant.now());
        shipmentArrivalRepository.save(shipmentArrival);
    }
    //************************************************************
    public ShipmentArrivalDto getShipmentArrivalByShipmentId(long shipmentId){
        ShipmentArrival shipmentArrival=  shipmentArrivalRepository.getShipmentArrivalByShipmentId(shipmentId);
        ShipmentArrivalDto shipmentArrivalDto = new ShipmentArrivalDto();
        shipmentArrivalDto.setShipmentArrivalId(shipmentArrival.getShipmentArrivalId());
        shipmentArrivalDto.setShipmentId(shipmentArrival.getShipmentId());
        shipmentArrivalDto.setGateNumber(shipmentArrival.getGateNumber());
        shipmentArrivalDto.setShipmentStatus(shipmentArrival.getShipmentStatus());
        shipmentArrivalDto.setDateCreated(shipmentArrival.getDateCreated());
        shipmentArrivalDto.setUpdatedByEmployeeId(shipmentArrival.getUpdatedByEmployeeId());
        return shipmentArrivalDto;
    }

}
