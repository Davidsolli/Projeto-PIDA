package com.david.amazonas.controllers;

import com.david.amazonas.domains.users.Seller;
import com.david.amazonas.dtos.users.SellerAuthenticationDTO;
import com.david.amazonas.dtos.users.SellerRegisterDTO;
import com.david.amazonas.repositories.SellerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SellerRepository repository;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid SellerAuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody @Valid SellerRegisterDTO data) {
        if (this.repository.findByEmail(data.getEmail()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        Seller seller = new Seller();
        seller.setEmail(data.getEmail());
        seller.setPassword(encryptedPassword);
        seller.setRole(data.getRole());
        seller.setBusinessName(data.getBusinessName());
        seller.setCnpj(data.getCnpj());
        seller.setPhoneNumber(data.getPhoneNumber());

        this.repository.save(seller);

        return ResponseEntity.ok().build();
    }
}
