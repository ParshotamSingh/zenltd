package com.zenltd.service;

import com.zenltd.enums.ProductInboundStatus;
import com.zenltd.enums.ProductInspectionStatus;
import com.zenltd.dto.InboundReceivingDto;
import com.zenltd.entity.InboundReceiving;
import com.zenltd.entity.Product;
import com.zenltd.repositories.InboundReceivingRepository;
import com.zenltd.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class InboundReceivingService extends RuntimeException {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InboundReceivingRepository inboundReceivingRepository;
    public void saveInboundService(InboundReceivingDto inboundReceivingDto) {

        Product product = productRepository.getProductByProductCode(inboundReceivingDto.getProductCode());
        InboundReceiving inbound = new InboundReceiving();
        if (product == null) {
            throw new IllegalArgumentException("Error: Product does not exist in system");
        }
        else {
            inbound.setProductCode(inboundReceivingDto.getProductCode());
            inbound.setShipmentId(product.getShipmentId());

            inbound.setInspectorId(inboundReceivingDto.getInspectorId());

            ProductInspectionStatus inspectionStatus = inboundReceivingDto.getProductInspectionStatus();
            inbound.setProductInspectionStatus(inspectionStatus);
            ProductInboundStatus productInboundStatus;
            switch (inspectionStatus) {
                case PASSED -> productInboundStatus = ProductInboundStatus.RECEIVED;
                case DAMAGED, EXPIRED, INCORRECT -> productInboundStatus = ProductInboundStatus.REJECTED;
                case QUARANTINE -> productInboundStatus = ProductInboundStatus.HOLD;
                default -> productInboundStatus = ProductInboundStatus.RETURNED;
            }
            inbound.setProductInboundStatus(productInboundStatus);

            inbound.setInspectedTime(LocalDateTime.now());

            inboundReceivingRepository.save(inbound);
        }
    }

    public List<Product> getProductsNotYetInboundReceived(){
        List<Product> allProducts = productRepository.findAll();

        List<InboundReceiving> listOfAllInboundProducts = inboundReceivingRepository.findAll();
        List<Long> listOfInboundProductCodes = new ArrayList<>();
        for(InboundReceiving x:listOfAllInboundProducts ){
            listOfInboundProductCodes.add(x.getProductCode());
        }

        List<Product> listOfMissingProducts = new ArrayList<>();
        for(Product k :allProducts){
            if(!listOfInboundProductCodes.contains(k.getProductCode())){
                listOfMissingProducts.add(k);
            }
        }
       return listOfMissingProducts;

    }

}
