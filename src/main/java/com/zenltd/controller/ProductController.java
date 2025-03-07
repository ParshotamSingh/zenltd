package com.zenltd.controller;

//import com.zenltd.dto.ContainerDto;
import com.zenltd.dto.ProductDto;
import com.zenltd.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Operation(summary = "Save Product")
    @PostMapping("/saveProduct")
    public void saveProduct(@Valid @RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
    }
    @Operation(summary = "Get Product By Id")
    @GetMapping(value = "/getProductById")
    public ResponseEntity<ProductDto> getProductById(@Valid @RequestParam long id) {
        ProductDto productDto   = productService.getProductById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
    @Operation(summary = "Get List Of Products In a Container")
    @GetMapping(value ="/getAllProductsByContainerId")
    public ResponseEntity<List<ProductDto>> getAllProductsByContainerId(@Valid @RequestParam long containerId){
         List<ProductDto> listOfProductDto = productService.getAllProductsByContainerId(containerId);
        return new ResponseEntity<>(listOfProductDto, HttpStatus.OK);

    }
}
