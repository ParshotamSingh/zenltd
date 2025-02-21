package com.zenltd.service;

import com.zenltd.enums.ShipmentInspectionStatus;
import com.zenltd.dto.ShipmentInspectionDto;
import com.zenltd.entity.ShipmentInspection;
import com.zenltd.repositories.ShipmentInspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentInspectionService {
    @Autowired
    ShipmentInspectionRepository shipmentInspectionRepository;
    public void saveStartInspection(ShipmentInspectionDto shipmentInspectionDto){
        ShipmentInspection shipmentInspection = new ShipmentInspection();
        shipmentInspection.setId(shipmentInspectionDto.getId());
        shipmentInspection.setShipmentId(shipmentInspectionDto.getShipmentId());
        shipmentInspection.setInspectorId(shipmentInspectionDto.getInspectorId());
        shipmentInspection.setRemarks(shipmentInspectionDto.getRemarks());
        shipmentInspection.setInspectionStatus(ShipmentInspectionStatus.START);
        shipmentInspection.setStartTime(LocalDateTime.now());
        shipmentInspectionRepository.save(shipmentInspection);
    }
    public void updateInspection(long shipmentId, long inspectorId, String remarks, ShipmentInspectionStatus shipmentInspectionStatus){
        ShipmentInspection shipmentInspection = shipmentInspectionRepository.getInspectionByShipmentId(shipmentId);
        shipmentInspection.setInspectorId(inspectorId);
        shipmentInspection.setInspectionStatus(shipmentInspectionStatus);
        shipmentInspection.setRemarks(remarks);
        shipmentInspection.setLastUpdatedTime(LocalDateTime.now());
        shipmentInspectionRepository.save(shipmentInspection);
    }
    public void completeInspection(long shipmentId, String remarks, ShipmentInspectionStatus shipmentInspectionStatus){
        ShipmentInspection shipmentInspection = shipmentInspectionRepository.getInspectionByShipmentId(shipmentId);
        shipmentInspection.setInspectionStatus(shipmentInspectionStatus); //Pass or Fail
        shipmentInspection.setRemarks(remarks);
        shipmentInspection.setEndTime(LocalDateTime.now());
        shipmentInspectionRepository.save(shipmentInspection);
    }
    public ShipmentInspectionDto getInspectionByShipmentId(long shipmentId){
        ShipmentInspection shipmentInspection = shipmentInspectionRepository.getInspectionByShipmentId(shipmentId);
        ShipmentInspectionDto shipmentInspectionDto = new ShipmentInspectionDto();
        shipmentInspectionDto.setId(shipmentInspection.getId());
        shipmentInspectionDto.setShipmentId(shipmentInspection.getShipmentId());
        shipmentInspectionDto.setInspectorId(shipmentInspection.getInspectorId());
        shipmentInspectionDto.setInspectionStatus(shipmentInspection.getInspectionStatus());
        shipmentInspectionDto.setRemarks(shipmentInspection.getRemarks());
        shipmentInspectionDto.setStartTime(shipmentInspection.getStartTime());
        shipmentInspectionDto.setLastUpdatedTime(shipmentInspection.getLastUpdatedTime());
        shipmentInspectionDto.setEndTime(shipmentInspection.getEndTime());
        return shipmentInspectionDto;
    }

    public List<ShipmentInspectionDto> getInspectionsByStatus(ShipmentInspectionStatus shipmentInspectionStatus) {
        List<ShipmentInspection> shipmentInspectionList = shipmentInspectionRepository.getInspectionsByStatus(shipmentInspectionStatus);
        List<ShipmentInspectionDto> shipmentInspectionDtos = new ArrayList<>();
        for (ShipmentInspection x : shipmentInspectionList) {
            ShipmentInspectionDto shipmentInspectionDto = new ShipmentInspectionDto();
            shipmentInspectionDto.setId(x.getId());
            shipmentInspectionDto.setShipmentId(x.getShipmentId());
            shipmentInspectionDto.setInspectorId(x.getInspectorId());
            shipmentInspectionDto.setInspectionStatus(x.getInspectionStatus());
            shipmentInspectionDto.setRemarks(x.getRemarks());
            shipmentInspectionDto.setStartTime(x.getStartTime());
            shipmentInspectionDto.setLastUpdatedTime(x.getLastUpdatedTime());
            shipmentInspectionDto.setEndTime(x.getEndTime());
            shipmentInspectionDtos.add(shipmentInspectionDto);
        }
        return shipmentInspectionDtos;
    }
    public List<ShipmentInspectionDto> getInspectionsByInspectorId (long inspectorId){
            List<ShipmentInspection> shipmentInspectionList = shipmentInspectionRepository.getInspectionsByInspectorId(inspectorId);
            List<ShipmentInspectionDto> shipmentInspectionDtos = new ArrayList<>();
            for (ShipmentInspection x : shipmentInspectionList) {
                ShipmentInspectionDto shipmentInspectionDto = new ShipmentInspectionDto();
                shipmentInspectionDto.setId(x.getId());
                shipmentInspectionDto.setShipmentId(x.getShipmentId());
                shipmentInspectionDto.setInspectorId(x.getInspectorId());
                shipmentInspectionDto.setInspectionStatus(x.getInspectionStatus());
                shipmentInspectionDto.setRemarks(x.getRemarks());
                shipmentInspectionDto.setStartTime(x.getStartTime());
                shipmentInspectionDto.setLastUpdatedTime(x.getLastUpdatedTime());
                shipmentInspectionDto.setEndTime(x.getEndTime());
                shipmentInspectionDtos.add(shipmentInspectionDto);
            }
            return shipmentInspectionDtos;

    }

}
