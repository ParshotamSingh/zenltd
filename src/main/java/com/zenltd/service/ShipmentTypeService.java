package com.zenltd.service;

import com.zenltd.dto.ShipmentDto;
import com.zenltd.dto.ShipmentTypeDto;
import com.zenltd.entity.Shipment;
import com.zenltd.entity.ShipmentType;
import com.zenltd.repositories.ShipmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentTypeService {
    @Autowired
    ShipmentTypeRepository shipmentTypeRepository;
    public void saveShipmentType(ShipmentTypeDto shipmentTypeDto) {
        ShipmentType shipmentType = new ShipmentType();
        shipmentType.setShipmentType(shipmentTypeDto.getShipmentType());
        shipmentType.setShipmentTypeCode(shipmentTypeDto.getShipmentTypeCode());
        shipmentType.setShipmentTypeName(shipmentTypeDto.getShipmentTypeName());
        shipmentTypeRepository.save(shipmentType);
    }

    public ShipmentTypeDto getShipmentTypeById(long id){
        ShipmentType shipmentType = shipmentTypeRepository.getShipmentTypeById(id);
        ShipmentTypeDto shipmentTypeDto = new ShipmentTypeDto();
        shipmentTypeDto.setId(shipmentType.getId());
        shipmentTypeDto.setShipmentType(shipmentType.getShipmentType());
        shipmentTypeDto.setShipmentTypeCode(shipmentType.getShipmentTypeCode());
        shipmentTypeDto.setShipmentTypeName(shipmentType.getShipmentTypeName());
        return shipmentTypeDto;
    }
}
