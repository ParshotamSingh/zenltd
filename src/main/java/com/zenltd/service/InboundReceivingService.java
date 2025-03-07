package com.zenltd.service;

import com.zenltd.dto.ProductDto;
import com.zenltd.entity.*;
import com.zenltd.enums.ProductInboundStatus;
import com.zenltd.enums.ProductInspectionStatus;
import com.zenltd.dto.InboundReceivingDto;
import com.zenltd.exception.EntityNotFoundException;
import com.zenltd.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
@Slf4j
@Service
public class InboundReceivingService extends RuntimeException {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InboundReceivingRepository inboundReceivingRepository;
    @Autowired
    private ShipmentPreparationRepository shipmentPreparationRepository;
    @Autowired
    private ShipmentStatusHistoryRepository shipmentStatusHistoryRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;

    public void saveInboundService(InboundReceivingDto inboundReceivingDto) {
        log.info("Saving Shipment Inspection & Inbound");
        //Check if scanned Product exists in product table or not.
        Product product = productRepository.getProductByProductCode(inboundReceivingDto.getProductCode());
        if (product == null) {
            log.error("No Product exists with Product Code: {}", inboundReceivingDto.getProductCode());
            throw new EntityNotFoundException("No Product exists with Product Code: " + inboundReceivingDto.getProductCode());
        }
//        if product is not null, shipment cannot be null obviously
//        So no need of nullCheck for shipmentId

        //Check if preparation of shipment of this scanned product was Completed or not.
        ShipmentPreparation shipmentPreparation = shipmentPreparationRepository.getPreparationStatusByShipmentId(product.getShipmentId());
        if (shipmentPreparation == null || !"Preparation Completed".equalsIgnoreCase(shipmentPreparation.getPreparationStatus())) {
            log.error("Inbound receiving failed: This product's shipment has not completed the required preparation stage.");
            throw new IllegalStateException("This product's shipment with Shipment Id: " + product.getShipmentId() + " has not met the preparation requirements. Please ensure all prior steps are finalized before proceeding.");
        }

//        Check if this product has already been inspected before & not kept on HOLD.
        InboundReceiving existingInspection = inboundReceivingRepository.getInboundReceivingByProductCode(inboundReceivingDto.getProductCode());
        if (existingInspection != null) {
            if (!existingInspection.getProductInboundStatus().equals(ProductInboundStatus.HOLD)) {
                log.error("Inbound receiving failed: Product {} has already been inspected and marked as {}.",
                        inboundReceivingDto.getProductCode(), existingInspection.getProductInboundStatus());
                throw new IllegalStateException("Inbound receiving failed: The product with code " + inboundReceivingDto.getProductCode() +
                        " has already been inspected and assigned the status: " + existingInspection.getProductInboundStatus() +
                        ". Further processing is not allowed.");
            }
            // If product was previously on HOLD, update its existing record instead of creating a new one
            log.info("Re-inspecting product {} that was previously on HOLD.", inboundReceivingDto.getProductCode());
            existingInspection.setInspectorId(inboundReceivingDto.getInspectorId());
            existingInspection.setProductInspectionStatus(inboundReceivingDto.getProductInspectionStatus());
            // Update inbound status based on new inspection status
            ProductInboundStatus updatedStatus = switch (inboundReceivingDto.getProductInspectionStatus()) {
                case PASSED -> ProductInboundStatus.RECEIVED;
                case DAMAGED, EXPIRED, INCORRECT -> ProductInboundStatus.REJECTED;
                case QUARANTINE -> ProductInboundStatus.HOLD;
                default -> ProductInboundStatus.RETURNED;
            };
            existingInspection.setProductInboundStatus(updatedStatus);
            existingInspection.setTimeStamp(LocalDateTime.now());
            inboundReceivingRepository.save(existingInspection);
            log.info("Updated existing inspection record for Product Code: {}", inboundReceivingDto.getProductCode());
        }


        else {
            log.info("First-time inspection for Product Code: {}", inboundReceivingDto.getProductCode());
            //ToDo Check whether Inspector Id exists in employee table or not
            InboundReceiving inbound = new InboundReceiving();
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

            inbound.setTimeStamp(LocalDateTime.now());

            inboundReceivingRepository.save(inbound);
        }

//        So that once first product of a shipment gets into inspection & inbound Stage, no multiple
//        records are made during inspection of its All products in history table for a shipment
//        Avoiding Redundancy
        boolean productOfShipmentUnderInspectionBefore = shipmentStatusHistoryRepository.existsByShipmentIdAndStatus(product.getShipmentId(), "Shipment Products Under Inspection");
        if (!productOfShipmentUnderInspectionBefore) {
            ShipmentStatusHistory shipmentStatusHistory = new ShipmentStatusHistory();
            shipmentStatusHistory.setShipmentId(product.getShipmentId());
            shipmentStatusHistory.setStatus("Shipment Products Under Inspection");
            shipmentStatusHistory.setUpdatedByUsername(inboundReceivingDto.getInspectorId());
            shipmentStatusHistory.setStatusTimestamp(LocalDateTime.now());
            shipmentStatusHistoryRepository.save(shipmentStatusHistory);
        }
    }

    public List<Product> getProductsNotYetInboundReceived() {
        log.info("Fetching List  Products pending for Inspected & Inbound.");
        List<Product> allProducts = productRepository.findAll();

        List<InboundReceiving> listOfAllInboundProducts = inboundReceivingRepository.findAll();
        List<Long> listOfInboundProductCodes = new ArrayList<>();
        for (InboundReceiving x : listOfAllInboundProducts) {
            listOfInboundProductCodes.add(x.getProductCode());
        }

        List<Product> listOfMissingProducts = new ArrayList<>();
        for (Product k : allProducts) {
            if (!listOfInboundProductCodes.contains(k.getProductCode())) {
                listOfMissingProducts.add(k);
            }
        }
        return listOfMissingProducts;

    }

    public void updateShipmentStatusIfAllProductsReceived(Long shipmentId, Long updatedByUsername) {
        log.info("Checking if all products for Shipment ID {} have been inspected and received.", shipmentId);
        // Get all products linked to the shipment
        List<Product> allProductsForShipment = productRepository.getAllProductsByShipmentId(shipmentId);

        // Get all received products
        List<InboundReceiving> receivedProducts = inboundReceivingRepository.findByShipmentIdAndProductInboundStatus(shipmentId, ProductInboundStatus.RECEIVED);

        // Get all rejected products
        List<InboundReceiving> rejectedProducts = inboundReceivingRepository.findByShipmentIdAndProductInboundStatus(shipmentId, ProductInboundStatus.REJECTED);

        String newStatus;

        if (!allProductsForShipment.isEmpty()) {
            if (receivedProducts.size() == allProductsForShipment.size()) {
                newStatus = "Shipment Inbound Received";  // All products received
            } else if (!receivedProducts.isEmpty() && !rejectedProducts.isEmpty()) {
                newStatus = "Shipment Partially Received";  // Some received, some rejected
            } else if (rejectedProducts.size() == allProductsForShipment.size()) {
                newStatus = "Shipment Inbound Rejected";  // All products rejected
            } else {
                return; // No status update needed if no products processed yet
            }

            boolean alreadyUpdated = shipmentStatusHistoryRepository.existsByShipmentIdAndStatus(shipmentId, newStatus);

            // Avoid redundant entries
            if (!alreadyUpdated) {
                ShipmentStatusHistory shipmentStatusHistory = new ShipmentStatusHistory();
                shipmentStatusHistory.setShipmentId(shipmentId);
                shipmentStatusHistory.setStatus(newStatus);
                shipmentStatusHistory.setUpdatedByUsername(updatedByUsername);
                shipmentStatusHistory.setStatusTimestamp(LocalDateTime.now());
                shipmentStatusHistoryRepository.save(shipmentStatusHistory);

                // Also update the Shipment entity
                Shipment shipment = shipmentRepository.getShipmentById(shipmentId);
                if (shipment != null) {
                    shipment.setCurrentStatus(newStatus);
                    shipment.setDateUpdated(LocalDateTime.now());
                    shipmentRepository.save(shipment);
                }
            }
        }
    }

    //get products of a shipment not yet inspected.
    public List<Product> getProductsNotYetInspectedInShipment(Long shipmentId) {
        log.info("Getting Uninspected products of Shipment with ID: {}.", shipmentId);
        //check if shipment exists or not
        Shipment shipment = shipmentRepository.getShipmentById(shipmentId);
        if (shipment == null) {
            log.error("No Shipment exists with ShipmentId {}", shipmentId);
            throw new EntityNotFoundException("No Shipment exists with ShipmentId : " + shipmentId);
        }
        // Get all products linked to the shipment
        List<Product> allProductsForShipment = productRepository.getAllProductsByShipmentId(shipmentId);
        // Get list of Inspected products to the shipment
        List<InboundReceiving> inboundReceivings = inboundReceivingRepository.getInboundReceivingByShipmentId(shipmentId);
        //check if inspection even started or not for this shipment
        List<InboundReceiving> inbounds = inboundReceivingRepository.getInboundReceivingByShipmentId(shipmentId);
        if (inbounds.isEmpty()) {
            log.error("Inspection of products of shipment {} has not started yet.", shipmentId);
            throw new EntityNotFoundException("Inspection process for shipment " + shipmentId + " has not started yet." +
                    "Ensure the shipment has not completed the required preparation stage before proceeding.");
        }
        List<Long> inspectedProductsCodes = new ArrayList<>();
        for (InboundReceiving x : inboundReceivings) {
            Long y = x.getProductCode();
            inspectedProductsCodes.add(y);
        }
        List<Product> listOfUninspectedProductsInShipment = new ArrayList<>();
        for (Product a : allProductsForShipment) {
            if (!inspectedProductsCodes.contains(a.getProductCode())) {
                listOfUninspectedProductsInShipment.add(a);
            }
        }
        if (!listOfUninspectedProductsInShipment.isEmpty()) {
            return listOfUninspectedProductsInShipment;
        }
        else {
            log.info("No uninspected products found for shipment:" + shipmentId);
            return Collections.emptyList();

        }
    }

}
