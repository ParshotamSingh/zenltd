package com.zenltd.service;
import com.zenltd.dto.ShipmentDto;
import com.zenltd.dto.ShipmentStatusHistoryDto;
import com.zenltd.entity.Shipment;
import com.zenltd.entity.ShipmentStatusHistory;
import com.zenltd.exception.EntityNotFoundException;
import com.zenltd.repositories.MemberRepository;
import com.zenltd.repositories.ShipmentRepository;
import com.zenltd.repositories.ShipmentStatusHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ShipmentService {
@Autowired
private ShipmentRepository shipmentRepository;
@Autowired
private MemberRepository memberRepository;
@Autowired
private ShipmentStatusHistoryRepository shipmentStatusHistoryRepository;

public void saveShipmentDraft(ShipmentDto shipmentDto) {
        log.info("Saving Shipment Draft");
        if (memberRepository.getMemberById(shipmentDto.getMemberId()) == null) {
                log.error("Member not found with MemberId {}", shipmentDto.getMemberId());
                throw new EntityNotFoundException("Member not found with MemberId : " + shipmentDto.getMemberId());
        }
        //ToDo if employee exists in employee table or not
        Shipment shipment = new Shipment();
        shipment.setMemberId(shipmentDto.getMemberId());
        shipment.setShipmentBarcodeId(shipmentDto.getShipmentBarcodeId());
        shipment.setBusinessUnitId(shipmentDto.getBusinessUnitId());
        shipment.setReferenceNumber(shipmentDto.getReferenceNumber());
        shipment.setTransportType(shipmentDto.getTransportType());
        shipment.setShipmentType(shipmentDto.getShipmentType());
        shipment.setSource(shipmentDto.getSource());
        shipment.setCreatedByUsername(shipmentDto.getCreatedByUsername());
        shipment.setDateCreated(LocalDateTime.now());
        shipment.setCurrentStatus("Shipment Created");
        shipment.setRemarks(shipmentDto.getRemarks());
        shipmentRepository.save(shipment);

        ShipmentStatusHistory shipmentStatusHistory = new ShipmentStatusHistory();
        shipmentStatusHistory.setShipmentId(shipment.getShipmentId());
        shipmentStatusHistory.setStatus("Shipment Created");
        shipmentStatusHistory.setUpdatedByUsername(shipmentDto.getCreatedByUsername());
        shipmentStatusHistory.setStatusTimestamp(LocalDateTime.now());
        shipmentStatusHistoryRepository.save(shipmentStatusHistory);
}
public void cancelShipment(long shipmentId, Long updatedByUsername,String remarks) {
        Shipment shipment = shipmentRepository.getShipmentById(shipmentId);
        if (shipment == null) {
                log.error("Shipment {} not found with ShipmentId ", shipmentId);
                throw new EntityNotFoundException("Shipment not found with ShipmentId : " + shipmentId);
        }
        // Check if the shipment was previously Cancelled in status history
        boolean wasConfirmedBefore = shipmentStatusHistoryRepository.existsByShipmentIdAndStatus(shipmentId, "Shipment Cancelled");
        if (wasConfirmedBefore) {
                log.error("Shipment {} was already Cancelled once and cannot be Cancelled again", shipmentId);
                throw new IllegalStateException("Shipment was already Cancelled earlier and cannot be Cancelled again.");
        }
        shipment.setCurrentStatus("Shipment Cancelled");
        shipment.setUpdatedByUsername(updatedByUsername);
        shipment.setDateUpdated(LocalDateTime.now());
        shipment.setRemarks(remarks);
        shipmentRepository.save(shipment);

        ShipmentStatusHistory shipmentStatusHistory = new ShipmentStatusHistory();
        shipmentStatusHistory.setShipmentId(shipmentId);
        shipmentStatusHistory.setStatus("Shipment Cancelled");
        shipmentStatusHistory.setUpdatedByUsername(updatedByUsername);
        shipmentStatusHistory.setStatusTimestamp(LocalDateTime.now());
        shipmentStatusHistoryRepository.save(shipmentStatusHistory);
}

public void confirmShipment(long shipmentId, long updatedByUsername,
                            LocalDateTime expectedDeliveryDate, String remarks) {
        Shipment shipment = shipmentRepository.getShipmentById(shipmentId);
        //Check if shipment exists
        if (shipment == null) {
                log.error("Shipment not found with ShipmentId {}", shipmentId);
                throw new EntityNotFoundException("Shipment not found with ShipmentId : " + shipmentId);
        }
        //Check if the shipment was cancelled previously
        if ("Shipment Cancelled".equalsIgnoreCase(shipment.getCurrentStatus())) {
                log.error("Shipment {} once Cancelled cannot be confirmed", shipmentId);
                throw new IllegalStateException("Shipment was Cancelled and cannot be confirmed. Please Create new Shipment");
        }
        // Check if the shipment was previously confirmed in status history
        boolean wasConfirmedBefore = shipmentStatusHistoryRepository.existsByShipmentIdAndStatus(shipmentId, "Shipment Confirmed");
        if (wasConfirmedBefore) {
                log.error("Shipment {} was already confirmed once and cannot be confirmed again", shipmentId);
                throw new IllegalStateException("Shipment was already confirmed earlier and cannot be reconfirmed.");
        }
        //ToDo if employee exists in employee table or not
        //Validate that the expected delivery date is in the future
        if (expectedDeliveryDate.isBefore(LocalDateTime.now())) {
                log.error("Expected delivery date {} is in the past", expectedDeliveryDate);
                throw new IllegalArgumentException("Expected delivery date must be in the future.");
        }
        shipment.setExpectedDeliveryDate(expectedDeliveryDate);
        shipment.setUpdatedByUsername(updatedByUsername);
        shipment.setDateUpdated(LocalDateTime.now());
        shipment.setCurrentStatus("Shipment Confirmed");
        shipment.setRemarks(remarks);
        shipmentRepository.save(shipment);

        ShipmentStatusHistory shipmentStatusHistory = new ShipmentStatusHistory();
        shipmentStatusHistory.setShipmentId(shipmentId);
        shipmentStatusHistory.setStatus("Shipment Confirmed");
        shipmentStatusHistory.setUpdatedByUsername(updatedByUsername);
        shipmentStatusHistory.setStatusTimestamp(LocalDateTime.now());
        shipmentStatusHistoryRepository.save(shipmentStatusHistory);
}
public ShipmentDto getShipmentById(long shipmentId) {
        log.info("Fetching Shipment by shipmentId {} ", shipmentId);
        Shipment shipment = shipmentRepository.getShipmentById(shipmentId);
        // NullCheck for Shipment
        if (shipment == null) {
                log.error("Shipment Not Found with shipmentId {}", shipmentId);
                throw new EntityNotFoundException("Shipment Not Found with shipmentId : " + shipmentId);
                }
        ShipmentDto shipmentDto = new ShipmentDto();
        shipmentDto.setShipmentId(shipment.getShipmentId());
        shipmentDto.setMemberId(shipment.getMemberId());
        shipmentDto.setShipmentBarcodeId(shipment.getShipmentBarcodeId());
        shipmentDto.setBusinessUnitId(shipment.getBusinessUnitId());
        shipmentDto.setReferenceNumber(shipment.getReferenceNumber());
        shipmentDto.setTransportType(shipment.getTransportType());
        shipmentDto.setShipmentType(shipment.getShipmentType());
        shipmentDto.setSource(shipment.getSource());
        shipmentDto.setCreatedByUsername(shipment.getCreatedByUsername());
        shipmentDto.setUpdatedByUsername(shipment.getUpdatedByUsername());
        shipmentDto.setDateCreated(shipment.getDateCreated());
        shipmentDto.setDateUpdated(shipment.getDateUpdated());
        shipmentDto.setExpectedDeliveryDate(shipment.getExpectedDeliveryDate());
        shipmentDto.setRemarks(shipment.getRemarks());
        //Current Status of Shipment from Status History table
        Optional<ShipmentStatusHistory> latestStatus = shipmentStatusHistoryRepository
                .findTopByShipmentIdOrderByStatusTimestampDesc(shipmentId);
        shipmentDto.setCurrentStatus(latestStatus.map(ShipmentStatusHistory::getStatus).orElse("No Status Available"));

        return shipmentDto;
}
public List<ShipmentDto> getShipmentDeliveriesForToday() {
        log.info("Fetching Shipment Deliveries to be done Today");
        List<ShipmentDto> shipmentDtos = new ArrayList<>();
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);

        List<Shipment> shipments = shipmentRepository.getShipmentDeliveriesForToday(startOfDay, endOfDay);
        for(Shipment x : shipments) {
                ShipmentDto shipmentDto = new ShipmentDto();
                shipmentDto.setShipmentId(x.getShipmentId());
                shipmentDto.setMemberId(x.getMemberId());
                shipmentDto.setShipmentBarcodeId(x.getShipmentBarcodeId());
                shipmentDto.setBusinessUnitId(x.getBusinessUnitId());
                shipmentDto.setReferenceNumber(x.getReferenceNumber());
                shipmentDto.setTransportType(x.getTransportType());
                shipmentDto.setShipmentType(x.getShipmentType());
                shipmentDto.setSource(x.getSource());
                shipmentDto.setCreatedByUsername(x.getCreatedByUsername());
                shipmentDto.setUpdatedByUsername(x.getUpdatedByUsername());
                shipmentDto.setDateCreated(x.getDateCreated());
                shipmentDto.setDateUpdated(x.getDateUpdated());
                shipmentDto.setExpectedDeliveryDate(x.getExpectedDeliveryDate());
                shipmentDto.setRemarks(x.getRemarks());
                Optional<ShipmentStatusHistory> latestStatus = shipmentStatusHistoryRepository
                        .findTopByShipmentIdOrderByStatusTimestampDesc(x.getShipmentId());
                shipmentDto.setCurrentStatus(latestStatus.map(ShipmentStatusHistory::getStatus).orElse("No Status Available"));
                shipmentDtos.add(shipmentDto);
        }
        return shipmentDtos;
}
public List<ShipmentStatusHistoryDto> getStatusHistoryByShipmentId (long shipmentId){
        log.info("Fetching Status History of a Shipment");
        Shipment shipment = shipmentRepository.getShipmentById(shipmentId);
        // NullCheck for Shipment
        if (shipment == null) {
                log.error("Shipment Not Found with shipmentId {}", shipmentId);
                throw new EntityNotFoundException("Shipment Not Found with shipmentId : " + shipmentId);
        }
        List<ShipmentStatusHistory> shipmentHistories = shipmentStatusHistoryRepository.getStatusHistoryByShipmentId(shipmentId);
        List<ShipmentStatusHistoryDto> ShipmentHistoryDtos = new ArrayList<>();
        for( ShipmentStatusHistory x :shipmentHistories ){
                ShipmentStatusHistoryDto shipmentStatusHistoryDto = new ShipmentStatusHistoryDto();
                shipmentStatusHistoryDto.setId(x.getId());
                shipmentStatusHistoryDto.setShipmentId(x.getShipmentId());
                shipmentStatusHistoryDto.setStatus(x.getStatus());
                shipmentStatusHistoryDto.setStatusTimestamp(x.getStatusTimestamp());
                shipmentStatusHistoryDto.setUpdatedByUsername(x.getUpdatedByUsername());
                ShipmentHistoryDtos.add(shipmentStatusHistoryDto);
        }
        return ShipmentHistoryDtos;
}
}