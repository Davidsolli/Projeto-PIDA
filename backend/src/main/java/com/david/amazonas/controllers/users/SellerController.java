package com.david.amazonas.controllers.users;

import com.david.amazonas.dtos.users.SellerListDTO;
import com.david.amazonas.services.users.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping(value = "/page")
    public ResponseEntity<Page<SellerListDTO>> findAll(Pageable pageable) {
        Page<SellerListDTO> result = sellerService.findAll(pageable);
        return ResponseEntity.ok(result);
    }
}
