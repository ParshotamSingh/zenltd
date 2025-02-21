package com.zenltd.controller;

import com.zenltd.dto.InboundReceivingDto;
import com.zenltd.dto.MemberDto;
import com.zenltd.entity.InboundReceiving;
import com.zenltd.entity.Product;
import com.zenltd.service.InboundReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
public class InboundReceivingController {
    @Autowired
    InboundReceivingService inboundReceivingService;
    @PostMapping(value = "/saveInboundService")
    public void saveInboundService(@RequestBody InboundReceivingDto inboundReceivingDto){

        inboundReceivingService.saveInboundService(inboundReceivingDto);
    }
    //*************************************************
    @GetMapping(value = "/getProductsNotYetInboundReceived")
    public ResponseEntity<List<Product>> getProductsNotYetInboundReceived() {
        List<Product> listOfMissingProducts = inboundReceivingService.getProductsNotYetInboundReceived();
        return new ResponseEntity<>(listOfMissingProducts, HttpStatus.OK);
    }
}
