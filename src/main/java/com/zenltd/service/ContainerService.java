package com.zenltd.service;
import com.zenltd.dto.ContainerDto;
import com.zenltd.entity.Container;
import com.zenltd.repositories.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContainerService {
    @Autowired
    ContainerRepository containerRepository;
    public void saveContainer(ContainerDto containerDto) {
        Container container = new Container();
        container.setId(containerDto.getId());
        container.setContainerCode(containerDto.getContainerCode());
        container.setShipmentId(containerDto.getShipmentId());
        containerRepository.save(container);
    }
    //************************************************************************************
    public ContainerDto getContainerById(long id){
        Container container= containerRepository.getContainerById(id);
        ContainerDto containerDto = new ContainerDto();
        containerDto.setId(container.getId());                          //1. Container ID
        containerDto.setContainerCode(container.getContainerCode());    //2. Container Code
        containerDto.setShipmentId(container.getShipmentId());          //3.Shipment ID
        return containerDto;
    }
    //**********************************************************************************
    public List<ContainerDto> getAllContainersByShipmentId(long shipmentId){
        List<ContainerDto> listOfContainerDto = new ArrayList<>();

        List<Container> listOfContainers = containerRepository.getAllContainersByShipmentId(shipmentId);
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

