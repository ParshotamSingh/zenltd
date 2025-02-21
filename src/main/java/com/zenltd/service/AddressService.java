package com.zenltd.service;

import com.zenltd.dto.AddressDto;
import com.zenltd.entity.Address;
import com.zenltd.exception.EntityNotFoundException;
import com.zenltd.repositories.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    public void saveAddress(AddressDto addressDto){
       log.info("Saving address");
        Address address = new Address();
        address.setBuildingNo(addressDto.getBuildingNo());
        address.setStreetNo(addressDto.getStreetNo());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setPinCode(addressDto.getPinCode());
        addressRepository.save(address);
    }

    public AddressDto getAddressById(long id){
        log.info("Fetching address by id {} ",id);
        Address address= addressRepository.getAddressById(id);
        // NullCheck for address
        if(address == null){
            log.error("Address Not Found with id {}",id);
            throw new EntityNotFoundException("Address Not Found with id : " + id);
        }
        AddressDto addressDto = new AddressDto();
        addressDto.setBuildingNo(address.getBuildingNo());
        addressDto.setStreetNo(address.getStreetNo());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setPinCode(address.getPinCode());
    return addressDto;
    }
}
