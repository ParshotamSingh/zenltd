package com.zenltd.service;

import com.zenltd.dto.ProductAttributeDto;
import com.zenltd.dto.ProductDto;
import com.zenltd.entity.*;
import com.zenltd.exception.EntityNotFoundException;
import com.zenltd.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    ShipmentStatusHistoryRepository shipmentStatusHistoryRepository;
    @Autowired
    private ContainerRepository containerRepository;
    @Autowired
    private ProductAttributeRepository productAttributeRepository;
    @Transactional
    public void saveProduct(@Valid ProductDto productDto){
        log.info("Saving Product");
        // NullCheck for ContainerId
        Container container = containerRepository.getContainerById(productDto.getContainerId());
        if(container == null){
            log.error("No Container found with ContainerId {}",productDto.getContainerId());
            throw new EntityNotFoundException("No Container found with ContainerId : " + productDto.getContainerId());
        }
        //Check if related shipment has been confirmed before
        Long shipmentId = container.getShipmentId();
        Boolean checkPriorConfirmationOfShipment = shipmentStatusHistoryRepository.existsByShipmentIdAndStatus(shipmentId,"Shipment Confirmed");
        log.info("Shipment confirmation check result: {}", checkPriorConfirmationOfShipment);
        if(checkPriorConfirmationOfShipment){
            log.error("Saving Product failed: Shipment {} for product code {} is already confirmed. New products cannot be added to this shipment.",
                    shipmentId, productDto.getProductCode());
            throw new IllegalStateException("Saving Product failed: The shipment with ID " + shipmentId +
                    " is already confirmed. Adding new products to this shipment is not allowed.");
        }
        Boolean checkPriorCancellationOfShipment = shipmentStatusHistoryRepository.existsByShipmentIdAndStatus(shipmentId,"Shipment Cancelled");
        log.info("Shipment Cancellation check result: {}", checkPriorCancellationOfShipment);
        if(checkPriorCancellationOfShipment){
            log.error("Saving Product failed: Shipment {} for product code {} is already cancelled. New products cannot be added to this shipment.",
                    shipmentId, productDto.getProductCode());
            throw new IllegalStateException("Saving Product failed: The shipment with ID " + shipmentId +
                    " is already cancelled. Adding new products to this shipment is not allowed.");
        }

        Product product =new Product();
        product.setProductCode(productDto.getProductCode());
        product.setContainerId(productDto.getContainerId());
        product.setShipmentId(container.getShipmentId());

        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setProductName(productDto.getProductAttributeDto().getProductName());
        productAttribute.setProductType(productDto.getProductAttributeDto().getProductType());
        productAttribute.setProductState(productDto.getProductAttributeDto().getProductState());
        productAttribute.setProductionState(productDto.getProductAttributeDto().getProductionState());
        productAttribute.setTemperatureRequirement(productDto.getProductAttributeDto().getTemperatureRequirement());
        productAttribute.setShelfLife(productDto.getProductAttributeDto().getShelfLife());
        productAttribute.setBreakability(productDto.getProductAttributeDto().getBreakability());
        productAttribute.setFlammability(productDto.getProductAttributeDto().getFlammability());
        productAttribute.setExplosibility(productDto.getProductAttributeDto().getExplosibility());
        productAttribute.setPerishability(productDto.getProductAttributeDto().getPerishability());
        productAttribute.setPackagingType(productDto.getProductAttributeDto().getPackagingType());
        productAttribute.setMaterial(productDto.getProductAttributeDto().getMaterial());
        productAttribute.setNoOfUnits(productDto.getProductAttributeDto().getNoOfUnits());
        productAttribute.setManufacturerDetails(productDto.getProductAttributeDto().getManufacturerDetails());
        productAttribute.setCountryOfOrigin(productDto.getProductAttributeDto().getCountryOfOrigin());
        productAttribute.setMarketedBy(productDto.getProductAttributeDto().getMarketedBy());
        productAttribute.setSeller(productDto.getProductAttributeDto().getSeller());
        productAttributeRepository.save(productAttribute);

        product.setProductAttributeId(productAttribute.getId());

        productRepository.save(product);
    }

    public ProductDto getProductById(long id) {
        log.info("Fetching Product by id {} ",id);
        Product product = productRepository.getProductById(id);
        // NullCheck for Product
        if(product == null){
            log.error("Product Not Found with id {}",id);
            throw new EntityNotFoundException("Product Not Found with id : " + id);
        }
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductCode(product.getProductCode());
        productDto.setContainerId(product.getContainerId());
        productDto.setShipmentId(product.getShipmentId());

        ProductAttribute productAttribute = productAttributeRepository.getProductAttributeById(product.getProductAttributeId());
        ProductAttributeDto productAttributeDto = new ProductAttributeDto();
        productAttributeDto.setId(productAttribute.getId());
        productAttributeDto.setProductName(productAttribute.getProductName());
        productAttributeDto.setProductType(productAttribute.getProductType());
        productAttributeDto.setProductState(productAttribute.getProductState());
        productAttributeDto.setProductionState(productAttribute.getProductionState());
        productAttributeDto.setTemperatureRequirement(productAttribute.getTemperatureRequirement());
        productAttributeDto.setShelfLife(productAttribute.getShelfLife());
        productAttributeDto.setBreakability(productAttribute.getBreakability());
        productAttributeDto.setFlammability(productAttribute.getFlammability());
        productAttributeDto.setExplosibility(productAttribute.getExplosibility());
        productAttributeDto.setPerishability(productAttribute.getPerishability());
        productAttributeDto.setPackagingType(productAttribute.getPackagingType());
        productAttributeDto.setMaterial(productAttribute.getMaterial());
        productAttributeDto.setNoOfUnits(productAttribute.getNoOfUnits());
        productAttributeDto.setManufacturerDetails(productAttribute.getManufacturerDetails());
        productAttributeDto.setCountryOfOrigin(productAttribute.getCountryOfOrigin());
        productAttributeDto.setMarketedBy(productAttribute.getMarketedBy());
        productAttributeDto.setSeller(productAttribute.getSeller());

        productDto.setProductAttributeDto(productAttributeDto);

        return productDto;
    }
    public List<ProductDto> getAllProductsByContainerId(long containerId){
        log.info("Fetching List of Products by containerId {} ",containerId);
        if(containerRepository.getContainerById(containerId)== null){
            log.error("No Container found with containerId {}",containerId);
            throw new EntityNotFoundException("No Container found with containerId : " + containerId);
        }
        List<Product> listOfProducts = productRepository.getAllProductsByContainerId(containerId);
        if(listOfProducts == null ||listOfProducts.isEmpty()){
            log.error("No Products found with ContainerId {}",containerId);
            throw new EntityNotFoundException("No Products found with ContainerId : " + containerId);
        }
        List<ProductDto> listOfProductDto = new ArrayList<>();
        for(Product products: listOfProducts){
            ProductDto productDto = new ProductDto();
            productDto.setId(products.getId());
            productDto.setContainerId(products.getContainerId());
            productDto.setProductCode(products.getProductCode());
            productDto.setShipmentId(products.getShipmentId());

            ProductAttribute productAttribute = productAttributeRepository.getProductAttributeById(products.getProductAttributeId());
            ProductAttributeDto productAttributeDto = new ProductAttributeDto();
            productAttributeDto.setId(productAttribute.getId());
            productAttributeDto.setProductName(productAttribute.getProductName());
            productAttributeDto.setProductType(productAttribute.getProductType());
            productAttributeDto.setProductState(productAttribute.getProductState());
            productAttributeDto.setProductionState(productAttribute.getProductionState());
            productAttributeDto.setTemperatureRequirement(productAttribute.getTemperatureRequirement());
            productAttributeDto.setShelfLife(productAttribute.getShelfLife());
            productAttributeDto.setBreakability(productAttribute.getBreakability());
            productAttributeDto.setFlammability(productAttribute.getFlammability());
            productAttributeDto.setExplosibility(productAttribute.getExplosibility());
            productAttributeDto.setPerishability(productAttribute.getPerishability());
            productAttributeDto.setPackagingType(productAttribute.getPackagingType());
            productAttributeDto.setMaterial(productAttribute.getMaterial());
            productAttributeDto.setNoOfUnits(productAttribute.getNoOfUnits());
            productAttributeDto.setManufacturerDetails(productAttribute.getManufacturerDetails());
            productAttributeDto.setCountryOfOrigin(productAttribute.getCountryOfOrigin());
            productAttributeDto.setMarketedBy(productAttribute.getMarketedBy());
            productAttributeDto.setSeller(productAttribute.getSeller());
            productDto.setProductAttributeDto(productAttributeDto);

            listOfProductDto.add(productDto);
        }
        return listOfProductDto;
    }
}
