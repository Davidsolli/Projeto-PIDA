package com.david.amazonas.controllers;

import com.david.amazonas.dtos.users.SellerDTO;
import com.david.amazonas.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    // Implementar inserção de senha.
    @PostMapping
    public ResponseEntity<SellerDTO> insert(@RequestBody SellerDTO sellerDTO) {

        sellerDTO = sellerService.insert(sellerDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/sellerId}").buildAndExpand(sellerDTO.getSellerId()).toUri();

        return ResponseEntity.created(uri).body(sellerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDTO> findById(@PathVariable Long id) {
        SellerDTO sellerDTO = sellerService.findById(id);
        return ResponseEntity.ok(sellerDTO);
    }

    @GetMapping
    public ResponseEntity<Page<SellerDTO>> findAll(Pageable pageable) {
        Page<SellerDTO> sellerDTOPage = sellerService.findAll(pageable);
        return ResponseEntity.ok(sellerDTOPage);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SellerDTO> update(@PathVariable Long id, @RequestBody SellerDTO sellerDTO) {
        sellerDTO = sellerService.update(id, sellerDTO);
        return ResponseEntity.ok(sellerDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sellerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
