package com.zenltd.repositories;

import com.zenltd.entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {
    @Query(value = "SELECT t FROM Container t where t.id = :id")
    Container getContainerById(Long id);
    @Query(value = "SELECT t FROM Container t where t.shipmentId = :shipmentId")
    List<Container> getAllContainersByShipmentId(Long shipmentId);

}


