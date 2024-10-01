package com.david.amazonas.controllers;

import com.david.amazonas.dtos.products.ProductDTO;
import com.david.amazonas.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO productDTO) {
        productDTO = productService.insert(productDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/productId}").buildAndExpand(productDTO.getProductId()).toUri();
        return ResponseEntity.created(uri).body(productDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productDTO = productService.update(id, productDTO);
        return ResponseEntity.ok(productDTO);
    }
}
