package com.zenltd.controller;

//import com.zenltd.dto.ContainerDto;
import com.zenltd.dto.ProductDto;
import com.zenltd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/saveProduct")
    public void saveProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
    }

//********************************************************************************
    @GetMapping(value = "/getProductById")
    public ResponseEntity<ProductDto> getProductById(@RequestParam long id) {
        ProductDto productDto   = productService.getProductById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
//*********************************************************************************
    @GetMapping(value ="/getAllProductsByContainerId")
    public ResponseEntity<List<ProductDto>> getAllProductsByContainerId(@RequestParam long containerId){
         List<ProductDto> listOfProductDto = productService.getAllProductsByContainerId(containerId);
        return new ResponseEntity<>(listOfProductDto, HttpStatus.OK);

    }
}
