package com.zenltd.service;

import com.zenltd.dto.ShipmentArrivalDto;
import com.zenltd.entity.Shipment;
import com.zenltd.entity.ShipmentArrival;
import com.zenltd.entity.ShipmentStatusHistory;
import com.zenltd.exception.EntityNotFoundException;
import com.zenltd.repositories.ShipmentArrivalRepository;
import com.zenltd.repositories.ShipmentRepository;
import com.zenltd.repositories.ShipmentStatusHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class ShipmentArrivalService {
    @Autowired
    private ShipmentArrivalRepository shipmentArrivalRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private ShipmentStatusHistoryRepository shipmentStatusHistoryRepository;
    public void saveShipmentArrival(ShipmentArrivalDto shipmentArrivalDto){
        log.info("Saving Shipment Arrival");
        Shipment shipment = shipmentRepository.getShipmentById(shipmentArrivalDto.getShipmentId());
        //check if shipment exists or not
        if(shipment==null){
            log.error("No Shipment exists with ShipmentId {}",shipmentArrivalDto.getShipmentId());
            throw new EntityNotFoundException("No Shipment exists with ShipmentId : " + shipmentArrivalDto.getShipmentId());
        }
        //Check if shipment was confirmed or not
        if(shipment.getExpectedDeliveryDate()==null){
            log.error("Shipment {} not confirmed yet.",shipmentArrivalDto.getShipmentId());
            throw new EntityNotFoundException("Shipment with Id : "+ shipmentArrivalDto.getShipmentId()+" has not been not confirmed by management yet. " +
                    "Unconfirmed  Shipments cannot arrive." );
        }
        // Fetch the shipment arrival record
        ShipmentArrival arrival = shipmentArrivalRepository.getShipmentArrivalByShipmentId(shipmentArrivalDto.getShipmentId());
        if(arrival!= null){
            //Check if shipment has already arrived
            if ("Arrived".equalsIgnoreCase(shipmentArrivalRepository.getShipmentArrivalByShipmentId(shipmentArrivalDto.getShipmentId()).getShipmentStatus())){
            log.error("Shipment {} has already arrived and cannot be marked as arrived again.", shipmentArrivalDto.getShipmentId());
            throw new IllegalStateException("Shipment with ID: " + shipmentArrivalDto.getShipmentId() +
                    " has already arrived. Duplicate arrival updates are not allowed.");
            }
        }
        ShipmentArrival shipmentArrival = new ShipmentArrival();
        //ToDo Check whether employee exists in employee table
        shipmentArrival.setShipmentId(shipmentArrivalDto.getShipmentId());
        shipmentArrival.setGateNumber(shipmentArrivalDto.getGateNumber());
        shipmentArrival.setShipmentStatus(shipmentArrivalDto.getShipmentStatus());
        shipmentArrival.setUpdatedByUsername(shipmentArrivalDto.getUpdatedByUsername());
        shipmentArrival.setDateCreated(LocalDateTime.now());
        shipmentArrivalRepository.save(shipmentArrival);

        ShipmentStatusHistory shipmentStatusHistory = new ShipmentStatusHistory();
        shipmentStatusHistory.setShipmentId(shipmentArrivalDto.getShipmentId());
        shipmentStatusHistory.setStatus("Shipment Arrived");
        shipmentStatusHistory.setUpdatedByUsername(shipmentArrivalDto.getUpdatedByUsername());
        shipmentStatusHistory.setStatusTimestamp(LocalDateTime.now());
        shipmentStatusHistoryRepository.save(shipmentStatusHistory);
    }


    public ShipmentArrivalDto getShipmentArrivalByShipmentId(long shipmentId){
        log.info("Fetching Shipment Arrival by Shipment Id");
        //Check if shipment Exists
        if(shipmentRepository.getShipmentById(shipmentId)==null){
            log.error("Shipment Not Found with ShipmentId {}",shipmentId);
            throw new EntityNotFoundException("Shipment Not Found with ShipmentId : " + shipmentId);
        }
        ShipmentArrival shipmentArrival=  shipmentArrivalRepository.getShipmentArrivalByShipmentId(shipmentId);

        if(shipmentArrival==null){
            log.error("Shipment with Id: {} has not arrived yet.",shipmentId);
            throw new EntityNotFoundException("Shipment with Id: " + shipmentId +" has not arrived yet");
        }
        ShipmentArrivalDto shipmentArrivalDto = new ShipmentArrivalDto();
        shipmentArrivalDto.setShipmentArrivalId(shipmentArrival.getShipmentArrivalId());
        shipmentArrivalDto.setShipmentId(shipmentArrival.getShipmentId());
        shipmentArrivalDto.setGateNumber(shipmentArrival.getGateNumber());
        shipmentArrivalDto.setShipmentStatus(shipmentArrival.getShipmentStatus());
        shipmentArrivalDto.setDateCreated(shipmentArrival.getDateCreated());
        shipmentArrivalDto.setUpdatedByUsername(shipmentArrival.getUpdatedByUsername());
        return shipmentArrivalDto;
    }

}
