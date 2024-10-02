package com.david.amazonas.controllers;

import com.david.amazonas.dtos.users.BuyerDTO;
import com.david.amazonas.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @PostMapping
    public ResponseEntity<BuyerDTO> insert(@RequestBody BuyerDTO buyerDTO) {
        buyerDTO = buyerService.insert(buyerDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("buyerId").buildAndExpand(buyerDTO.getBuyerId()).toUri();
        return ResponseEntity.created(uri).body(buyerDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BuyerDTO> findById(@PathVariable Long id) {
        BuyerDTO buyerDTO = buyerService.findById(id);
        return ResponseEntity.ok(buyerDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BuyerDTO> update(@PathVariable Long id, @RequestBody BuyerDTO buyerDTO) {
        buyerDTO = buyerService.update(id, buyerDTO);
        return ResponseEntity.ok(buyerDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        buyerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
