package com.zenltd.service;

import com.zenltd.dto.ProductDto;
import com.zenltd.entity.Container;
import com.zenltd.entity.Product;
import com.zenltd.repositories.ContainerRepository;
import com.zenltd.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ContainerRepository containerRepository;
    public void saveProduct(ProductDto productDto){
        Product product =new Product();
        product.setId(productDto.getId());
        product.setProductCode(productDto.getProductCode());
        product.setContainerId(productDto.getContainerId());
        product.setProductName(productDto.getProductName());
        product.setProductType(productDto.getProductType());
        product.setProductState(productDto.getProductState());
        product.setProductionState(productDto.getProductionState());
        product.setTemperatureRequirement(productDto.getTemperatureRequirement());
        product.setShelfLife(productDto.getShelfLife());
        product.setBreakability(productDto.getBreakability());
        product.setFlammability(productDto.getFlammability());
        product.setExplosibility(productDto.getExplosibility());
        product.setPerishability(productDto.getPerishability());
        product.setPackagingType(productDto.getPackagingType());
        product.setMaterial(productDto.getMaterial());
        product.setNoOfUnits(productDto.getNoOfUnits());
        product.setManufacturerDetails(productDto.getManufacturerDetails());
        product.setCountryOfOrigin(productDto.getCountryOfOrigin());
        product.setMarketedBy(productDto.getMarketedBy());
        product.setSeller(productDto.getSeller());

        Container container = containerRepository.getContainerById(productDto.getContainerId());
        product.setShipmentId(container.getShipmentId());

        productRepository.save(product);
    }
//**********************************************************************************************
    public ProductDto getProductById(long id) {
        Product product = productRepository.getProductById(id);
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductCode(product.getProductCode());
        productDto.setContainerId(product.getContainerId());
        productDto.setShipmentId(product.getShipmentId());
        productDto.setProductName(product.getProductName());
        productDto.setProductType(product.getProductType());
        productDto.setProductState(product.getProductState());
        productDto.setProductionState(product.getProductionState());
        productDto.setTemperatureRequirement(product.getTemperatureRequirement());
        productDto.setShelfLife(product.getShelfLife());
        productDto.setBreakability(product.getBreakability());
        productDto.setFlammability(product.getFlammability());
        productDto.setExplosibility(product.getExplosibility());
        productDto.setPerishability(product.getPerishability());
        productDto.setPackagingType(product.getPackagingType());
        productDto.setMaterial(product.getMaterial());
        productDto.setNoOfUnits(product.getNoOfUnits());
        productDto.setManufacturerDetails(product.getManufacturerDetails());
        productDto.setCountryOfOrigin(product.getCountryOfOrigin());
        productDto.setMarketedBy(product.getMarketedBy());
        productDto.setSeller(product.getSeller());

        return productDto;
    }
//**********************************************************************************************
    public List<ProductDto> getAllProductsByContainerId(long containerId){
        List<ProductDto> listOfProductDto = new ArrayList<>();

        List<Product> listOfProducts = productRepository.getAllProductsByContainerId(containerId);

        for(Product products: listOfProducts){
            ProductDto productDto = new ProductDto();

            productDto.setId(products.getId());
            productDto.setContainerId(products.getContainerId());
            productDto.setProductCode(products.getProductCode());
            productDto.setShipmentId(products.getShipmentId());
            productDto.setProductName(products.getProductName());
            productDto.setProductType(products.getProductType());
            productDto.setProductState(products.getProductState());
            productDto.setProductionState(products.getProductionState());
            productDto.setTemperatureRequirement(products.getTemperatureRequirement());
            productDto.setShelfLife(products.getShelfLife());
            productDto.setBreakability(products.getBreakability());
            productDto.setFlammability(products.getFlammability());
            productDto.setExplosibility(products.getExplosibility());
            productDto.setPerishability(products.getPerishability());
            productDto.setPackagingType(products.getPackagingType());
            productDto.setMaterial(products.getMaterial());
            productDto.setNoOfUnits(products.getNoOfUnits());
            productDto.setManufacturerDetails(products.getManufacturerDetails());
            productDto.setCountryOfOrigin(products.getCountryOfOrigin());
            productDto.setMarketedBy(products.getMarketedBy());
            productDto.setSeller(products.getSeller());

            listOfProductDto.add(productDto);
        }
        return listOfProductDto;
    }
}
