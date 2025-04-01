package com.zenltd.service;
import com.zenltd.dto.ContainerDto;
import com.zenltd.entity.Container;
import com.zenltd.entity.ShipmentStatusHistory;
import com.zenltd.exception.EntityNotFoundException;
import com.zenltd.repositories.ContainerRepository;
import com.zenltd.repositories.ShipmentRepository;
import com.zenltd.repositories.ShipmentStatusHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@Service
public class ContainerService {
    @Autowired
    private ContainerRepository containerRepository;
    @Autowired
    ShipmentStatusHistoryRepository shipmentStatusHistoryRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;
    public void saveContainer(ContainerDto containerDto) {
        log.info("Saving Container");
//        null Check for shipmentId
        if(shipmentRepository.getShipmentById(containerDto.getShipmentId()) == null){
            throw new RuntimeException("Shipment Id does not exist");
        }
        //Check if related shipment has been confirmed before
        Long shipmentId = containerDto.getShipmentId();
        Boolean checkPriorConfirmationOfShipment = shipmentStatusHistoryRepository.existsByShipmentIdAndStatus(shipmentId,"Shipment Confirmed");
        if(checkPriorConfirmationOfShipment){
            log.error("Saving Container failed: Shipment {} is already confirmed.", shipmentId +
                    "New Container cannot be added to this shipment.");
            throw new IllegalStateException("Saving Container failed: The shipment with ID " + shipmentId +
                    " is already confirmed. Adding new Containers to this shipment is not allowed.");
        }
        //Check if related shipment has been Cancelled before
        Boolean checkPriorCancellationOfShipment = shipmentStatusHistoryRepository.existsByShipmentIdAndStatus(shipmentId,"Shipment Cancelled");
        if(checkPriorCancellationOfShipment){
            log.error("Saving Container failed: Shipment {} is already cancelled.", shipmentId +
                    "New Container cannot be added to this shipment.");
            throw new IllegalStateException("Saving Container failed: The shipment with ID " + shipmentId +
                    " is already cancelled. Adding new Containers to this shipment is not allowed.");
        }
        Container container = new Container();
        container.setContainerCode(containerDto.getContainerCode());
        container.setShipmentId(containerDto.getShipmentId());
        containerRepository.save(container);
    }
    public ContainerDto getContainerById(long id){
        log.info("Fetching Container by id {} ",id);
        Container container= containerRepository.getContainerById(id);
        if(container == null){
            log.error("Container Not Found with id {}",id);
            throw new EntityNotFoundException("Container Not Found with id : " + id);
        }
        ContainerDto containerDto = new ContainerDto();
        containerDto.setId(container.getId());
        containerDto.setContainerCode(container.getContainerCode());
        containerDto.setShipmentId(container.getShipmentId());
        return containerDto;
    }
    public List<ContainerDto> getAllContainersByShipmentId(long shipmentId){
        log.info("Fetching List of Containers by ShipmentId {} ",shipmentId);
        if(shipmentRepository.getShipmentById(shipmentId)== null){
            log.error("No Shipment found with shipmentId {}",shipmentId);
            throw new EntityNotFoundException("No Shipment found with shipmentId : " + shipmentId);
        }
        List<Container> listOfContainers = containerRepository.getAllContainersByShipmentId(shipmentId);
        if(listOfContainers == null|| listOfContainers.isEmpty()){
            log.error("No Containers found with shipmentId {}",shipmentId);
            throw new EntityNotFoundException("No Containers found with shipmentId : " + shipmentId);
        }
        List<ContainerDto> listOfContainerDto = new ArrayList<>();
        for(Container containers : listOfContainers){
            ContainerDto containerDto = new ContainerDto();
            containerDto.setId(containers.getId());
            containerDto.setContainerCode(containers.getContainerCode());
            containerDto.setShipmentId(containers.getShipmentId());
            listOfContainerDto.add(containerDto);
        }
        return listOfContainerDto;
    }
}

