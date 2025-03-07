package com.zenltd.repositories;

import com.zenltd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductCode(Long productCode); //whether  ProductCode already exists or not
    @Query(value = "SELECT t FROM Product t where t.id = :id")
    Product getProductById(Long id);
    @Query(value = "SELECT t FROM Product t where t.containerId = :containerId")
     List<Product> getAllProductsByContainerId(Long containerId);
    @Query(value = "SELECT t FROM Product t where t.shipmentId = :shipmentId")
    List<Product> getAllProductsByShipmentId(Long shipmentId);
    @Query(value = "SELECT t FROM Product t where t.productCode = :productCode")
    Product getProductByProductCode(Long productCode);


}