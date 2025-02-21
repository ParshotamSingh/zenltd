package com.zenltd.service;

import com.zenltd.enums.ShipmentPreparationStatus;
import com.zenltd.dto.ShipmentPreparationDto;
import com.zenltd.entity.ShipmentPreparation;
import com.zenltd.repositories.ShipmentPreparationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class ShipmentPreparationService {
    @Autowired
    ShipmentPreparationRepository shipmentPreparationRepository;
    public void saveStartPreparation(ShipmentPreparationDto shipmentPreparationDto){
        ShipmentPreparation shipmentPreparation = new ShipmentPreparation();
        shipmentPreparation.setId(shipmentPreparationDto.getId());
        shipmentPreparation.setShipmentId(shipmentPreparationDto.getShipmentId());
        shipmentPreparation.setStatus(shipmentPreparationDto.getStatus());
        shipmentPreparation.setGateNumber(shipmentPreparationDto.getGateNumber());
        shipmentPreparation.setRemarks(shipmentPreparationDto.getRemarks());
        shipmentPreparation.setStartTime(LocalDateTime.now());
        shipmentPreparationRepository.save(shipmentPreparation);
    }
    public void updateShipmentPreparation(long shipmentId,ShipmentPreparationStatus status, String remarks){
        ShipmentPreparation shipmentPreparation = shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId);
        shipmentPreparation.setStatus(status);
        shipmentPreparation.setRemarks(remarks);
        shipmentPreparation.setLastUpdatedTime(LocalDateTime.now());
        shipmentPreparationRepository.save(shipmentPreparation);
    }
    public void completeShipmentPreparation(long shipmentId, String remarks){
        ShipmentPreparation shipmentPreparation = shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId);
        shipmentPreparation.setStatus(ShipmentPreparationStatus.COMPLETED);
        shipmentPreparation.setRemarks(remarks);
        shipmentPreparation.setEndTime(LocalDateTime.now());
        shipmentPreparationRepository.save(shipmentPreparation);
    }
    public ShipmentPreparationDto getPreparationStatusByShipmentId(long shipmentId){
        ShipmentPreparationDto shipmentPreparationDto   = new ShipmentPreparationDto();
        ShipmentPreparation shipmentPreparation = shipmentPreparationRepository.getPreparationStatusByShipmentId(shipmentId);
        shipmentPreparationDto.setId(shipmentPreparation.getId());
        shipmentPreparationDto.setShipmentId(shipmentPreparation.getShipmentId());
        shipmentPreparationDto.setStatus(shipmentPreparation.getStatus());
        shipmentPreparationDto.setGateNumber(shipmentPreparation.getGateNumber());
        shipmentPreparationDto.setRemarks(shipmentPreparation.getRemarks());
        shipmentPreparationDto.setStartTime(shipmentPreparation.getStartTime());
        shipmentPreparationDto.setLastUpdatedTime(shipmentPreparation.getLastUpdatedTime());
        shipmentPreparationDto.setEndTime(shipmentPreparation.getEndTime());

        return shipmentPreparationDto;
    }
    public List<ShipmentPreparationDto> getShipmentsByPreparationStatus(ShipmentPreparationStatus status){
       List<ShipmentPreparation> shipmentPreparationList = shipmentPreparationRepository.getShipmentsByPreparationStatus(status);
        List<ShipmentPreparationDto> shipmentPreparationDtos = new ArrayList<>();
        for(ShipmentPreparation x:shipmentPreparationList){
            ShipmentPreparationDto shipmentPreparationDto = new ShipmentPreparationDto();
            shipmentPreparationDto.setId(x.getId());
            shipmentPreparationDto.setShipmentId(x.getShipmentId());
            shipmentPreparationDto.setGateNumber(x.getGateNumber());
            shipmentPreparationDto.setStatus(x.getStatus());
            shipmentPreparationDto.setStartTime(x.getStartTime());
            shipmentPreparationDto.setLastUpdatedTime(x.getLastUpdatedTime());
            shipmentPreparationDto.setEndTime(x.getEndTime());
            shipmentPreparationDto.setRemarks(x.getRemarks());
            shipmentPreparationDtos.add(shipmentPreparationDto);
        }
        return shipmentPreparationDtos;
    }

}
