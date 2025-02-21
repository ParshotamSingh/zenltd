package com.zenltd.controller;

import com.zenltd.dto.AddressDto;
import com.zenltd.dto.MemberDto;
import com.zenltd.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {
    @Autowired
    AddressService addressService;
    @PostMapping(value = "/saveAddress")
    public void saveAddress(@RequestBody AddressDto addressDto){
        addressService.saveAddress(addressDto);
    }
    //*********************************************************
    @Operation(summary = "Get Address By Id")
    @GetMapping(value = "/getAddressById")
    public ResponseEntity<AddressDto> getAddressById(@RequestParam long id) {
        AddressDto addressDto = addressService.getAddressById(id);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }
}