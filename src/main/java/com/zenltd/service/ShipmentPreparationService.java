package com.zenltd.service;

import com.zenltd.entity.Shipment;
import com.zenltd.entity.ShipmentStatusHistory;
import com.zenltd.dto.ShipmentPreparationDto;
import com.zenltd.entity.ShipmentPreparation;
import com.zenltd.exception.EntityNotFoundException;
import com.zenltd.repositories.ShipmentPreparationRepository;
import com.zenltd.repositories.ShipmentRepository;
import com.zenltd.repositories.ShipmentStatusHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class ShipmentPreparationService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private ShipmentPreparationRepository shipmentPreparationRepository;
    @Autowired
    private ShipmentStatusHistoryRepository shipmentStatusHistoryRepository;
    public void saveStartPreparation(ShipmentPreparationDto shipmentPreparationDto){
        log.info("Saving Shipment Preparation");
        Shipment shipment = shipmentRepository.getShipmentById(shipmentPreparationDto.getShipmentId());
        //check if shipment exists or not
        if(shipment==null){
            log.error("No Shipment exists with ShipmentId {}",shipmentPreparationDto.getShipmentId());
            throw new EntityNotFoundException("No Shipment exists with ShipmentId : " + shipmentPreparationDto.getShipmentId());
        }

        //Check if shipment has arrived or not as per status history
        String status1 = "Shipment Arrived";
        boolean alreadyArrived = shipmentStatusHistoryRepository.existsByShipmentIdAndStatus(shipmentPreparationDto.getShipmentId(),
                status1);
        if (!alreadyArrived) {
            log.error("Shipment {} not arrived yet.",shipmentPreparationDto.getShipmentId());
            throw new EntityNotFoundException("Shipment with Id : "+ shipmentPreparationDto.getShipmentId()+" has not arrived yet. " +
                    "In-Transit or Unconfirmed Shipments cannot be prepared." );
        }
        //Check if Shipment is already under Preparation
        String status2 = "Shipment Under Preparation";
        boolean alreadyUnderPreparation = shipmentStatusHistoryRepository.existsByShipmentIdAndStatus(shipmentPreparationDto.getShipmentId(),
                status2);
        if (alreadyUnderPreparation) {
            log.error("Shipment {} is already under Preparation.",shipmentPreparationDto.getShipmentId());
            throw new EntityNotFoundException("Preparation for Shipment with Id : "+ shipmentPreparationDto.getShipmentId()+"  has already started and cannot be restarted." +
                    "Please proceed with the existing preparation process.");
        }
        //ToDo Check whether employee exists in employee table
        ShipmentPreparation shipmentPreparation = new ShipmentPreparation();
        shipmentPreparation.setShipmentId(shipmentPreparationDto.getShipmentId());
        shipmentPreparation.setPreparationStatus(shipmentPreparationDto.getPreparationStatus());
        shipmentPreparation.setGateNumber(shipmentPreparationDto.getGateNumber());
        shipmentPreparation.setRemarks(shipmentPreparationDto.getRemarks());
        shipmentPreparation.setStartTime(LocalDateTime.now());
        shipmentPreparation.setUpdatedByUsername(shipmentPreparationDto.getUpdatedByUsername());
        shipmentPreparationRepository.save(shipmentPreparation);

        ShipmentStatusHistory shipmentStatusHistory = new ShipmentStatusHistory();
        shipmentStatusHistory.setShipmentId(shipmentPreparationDto.getShipmentId());
        shipmentStatusHistory.setStatus("Shipment Under Preparation");
        shipmentStatusHistory.setUpdatedByUsername(shipmentPreparationDto.getUpdatedByUsername());
        shipmentStatusHistory.setStatusTimestamp(LocalDateTime.now());
        shipmentStatusHistoryRepository.save(shipmentStatusHistory);
    }
    public void cancelShipmentPreparation(long shipmentId,String remarks, long UpdatedByUsername){
        log.info("Cancelling Shipment Preparation");
        //check if shipment exists or not
        Shipment shipment = shipmentRepository.getShipmentById(shipmentId);
        if(shipment==null){
            log.error("No Shipment exists with ShipmentId {}",shipmentId);
            throw new EntityNotFoundException("No Shipment exists with ShipmentId : " + shipmentId);
        }
        //check whether shipment preparation ever started for this shipment.
        if(shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId)==null){
            log.error("The Shipment {} never arrived for preparation.",shipmentId);
            throw new EntityNotFoundException("The Shipment "+ shipmentId + " never arrived for preparation.");
        }
        ShipmentPreparation shipmentPreparation = shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId);
        shipmentPreparation.setPreparationStatus("Preparation Cancelled");
        shipmentPreparation.setRemarks(remarks);
        shipmentPreparation.setEndTime(LocalDateTime.now());
        shipmentPreparation.setUpdatedByUsername(UpdatedByUsername);
        shipmentPreparationRepository.save(shipmentPreparation);

        ShipmentStatusHistory shipmentStatusHistory = new ShipmentStatusHistory();
        shipmentStatusHistory.setShipmentId(shipmentId);
        shipmentStatusHistory.setStatus("Shipment Preparation Cancelled");
        shipmentStatusHistory.setUpdatedByUsername(UpdatedByUsername);
        shipmentStatusHistory.setStatusTimestamp(LocalDateTime.now());
        shipmentStatusHistoryRepository.save(shipmentStatusHistory);
    }
    public void updateShipmentPreparation(long shipmentId,String preparationStatus,
                                          String remarks,long UpdatedByUsername){
        log.info("Updating Shipment Preparation");
        //check if shipment exists or not
        Shipment shipment = shipmentRepository.getShipmentById(shipmentId);
        if(shipment==null){
            log.error("No Shipment exists with ShipmentId {}",shipmentId);
            throw new EntityNotFoundException("No Shipment exists with ShipmentId : " + shipmentId);
        }
        //check whether shipment preparation ever started for this shipment.
        if(shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId)==null){
            log.error("The Shipment {} never arrived for preparation.",shipmentId);
            throw new EntityNotFoundException("The Shipment "+ shipmentId + " never arrived for preparation.");
        }

        String checkStatus = shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId).getPreparationStatus();
        //Check if shipment was cancelled while preparation
        if("Preparation Cancelled".equalsIgnoreCase(checkStatus)){
            log.error("The Shipment {} was cancelled while preparation.",shipmentId);
            throw new IllegalStateException ("The Shipment "+ shipmentId + " was cancelled while preparation. " +
                    "Cancelled shipments cannot be updated.");
        }
        //Check if shipment was finalised while preparation
        if("Preparation Completed".equalsIgnoreCase(checkStatus)){
            log.error("The Shipment {} was finalised after preparation.",shipmentId);
            throw new IllegalStateException ("The Shipment "+ shipmentId + " was finalised after preparation. " +
                    "Once Finalised shipments cannot be updated.");
        }

        ShipmentPreparation shipmentPreparation = shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId);
        shipmentPreparation.setPreparationStatus(preparationStatus);
        shipmentPreparation.setRemarks(remarks);
        shipmentPreparation.setLastUpdatedTime(LocalDateTime.now());
        shipmentPreparation.setUpdatedByUsername(UpdatedByUsername);
        shipmentPreparationRepository.save(shipmentPreparation);
    }

    public void finaliseShipmentPreparation(long shipmentId,String remarks,long UpdatedByUsername){
        log.info("Finalising Shipment Preparation");
        //check if shipment exists or not
        Shipment shipment = shipmentRepository.getShipmentById(shipmentId);
        if(shipment==null){
            log.error("No Shipment exists with ShipmentId {}",shipmentId);
            throw new EntityNotFoundException("No Shipment exists with ShipmentId : " + shipmentId);
        }
        //check if shipment preparation was started for this shipment.
        if(shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId)==null){
            log.error("The Shipment {} never arrived for preparation.",shipmentId);
            throw new EntityNotFoundException("The Shipment "+ shipmentId + " never arrived for preparation.");
        }

        String checkStatus = shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId).getPreparationStatus();
        //Check if shipment was cancelled while preparation
        if("Preparation Cancelled".equalsIgnoreCase(checkStatus)){
            log.error("The Shipment {} was cancelled while preparation.",shipmentId);
            throw new IllegalStateException ("The Shipment "+ shipmentId + " was cancelled while preparation. " +
                    "Once Cancelled shipments cannot be finalised.");
        }
        //Check if shipment is already finalised while preparation
        if ("Preparation Completed".equalsIgnoreCase(checkStatus)){
            log.error("The Shipment {} is already finalised after preparation.",shipmentId);
            throw new IllegalStateException("Shipment with ID: " + shipmentId +
                    " has already been finalized. A finalized shipment cannot be processed again.");
        }
        ShipmentPreparation shipmentPreparation = shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId);
        shipmentPreparation.setPreparationStatus("Preparation Completed");
        shipmentPreparation.setRemarks(remarks);
        shipmentPreparation.setEndTime(LocalDateTime.now());
        shipmentPreparation.setUpdatedByUsername(UpdatedByUsername);
        shipmentPreparationRepository.save(shipmentPreparation);

        ShipmentStatusHistory shipmentStatusHistory = new ShipmentStatusHistory();
        shipmentStatusHistory.setShipmentId(shipmentId);
        shipmentStatusHistory.setStatus("Shipment Preparation Completed");
        shipmentStatusHistory.setUpdatedByUsername(UpdatedByUsername);
        shipmentStatusHistory.setStatusTimestamp(LocalDateTime.now());
        shipmentStatusHistoryRepository.save(shipmentStatusHistory);
    }
    public ShipmentPreparationDto getPreparationStatusByShipmentId(long shipmentId){
        log.info("Getting Shipment Preparation by Shipment Id");
        //check if shipment exists or not
        if(shipmentRepository.getShipmentById(shipmentId)==null){
            log.error("No Shipment exists with ShipmentId {}",shipmentId);
            throw new EntityNotFoundException("No Shipment exists with ShipmentId : " + shipmentId);
        }
        //check if shipment ever arrived for Preparation Stage or not.
        if(shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId)==null){
            log.error("The Shipment {} has not arrived for preparation.",shipmentId);
            throw new EntityNotFoundException("The Shipment "+ shipmentId + " has not arrived for preparation.");
        }
        ShipmentPreparationDto shipmentPreparationDto = new ShipmentPreparationDto();
        ShipmentPreparation shipmentPreparation = shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId);
        shipmentPreparationDto.setId(shipmentPreparation.getId());
        shipmentPreparationDto.setShipmentId(shipmentPreparation.getShipmentId());
        shipmentPreparationDto.setPreparationStatus(shipmentPreparation.getPreparationStatus());
        shipmentPreparationDto.setGateNumber(shipmentPreparation.getGateNumber());
        shipmentPreparationDto.setRemarks(shipmentPreparation.getRemarks());
        shipmentPreparationDto.setStartTime(shipmentPreparation.getStartTime());
        shipmentPreparationDto.setLastUpdatedTime(shipmentPreparation.getLastUpdatedTime());
        shipmentPreparationDto.setEndTime(shipmentPreparation.getEndTime());
        shipmentPreparationDto.setUpdatedByUsername(shipmentPreparation.getUpdatedByUsername());

        return shipmentPreparationDto;
    }
    public List<ShipmentPreparationDto> getShipmentsByPreparationStatus(String status){
        log.info("Fetching List of Shipments as their Shipment Preparation Status");
       List<ShipmentPreparation> shipmentPreparationList = shipmentPreparationRepository.getShipmentsByPreparationStatus(status);
        List<ShipmentPreparationDto> shipmentPreparationDtos = new ArrayList<>();
        for(ShipmentPreparation x:shipmentPreparationList){
            ShipmentPreparationDto shipmentPreparationDto = new ShipmentPreparationDto();
            shipmentPreparationDto.setId(x.getId());
            shipmentPreparationDto.setShipmentId(x.getShipmentId());
            shipmentPreparationDto.setGateNumber(x.getGateNumber());
            shipmentPreparationDto.setPreparationStatus(x.getPreparationStatus());
            shipmentPreparationDto.setStartTime(x.getStartTime());
            shipmentPreparationDto.setLastUpdatedTime(x.getLastUpdatedTime());
            shipmentPreparationDto.setEndTime(x.getEndTime());
            shipmentPreparationDto.setRemarks(x.getRemarks());
            shipmentPreparationDto.setUpdatedByUsername(x.getUpdatedByUsername());
            shipmentPreparationDtos.add(shipmentPreparationDto);
        }
        return shipmentPreparationDtos;
    }

}
