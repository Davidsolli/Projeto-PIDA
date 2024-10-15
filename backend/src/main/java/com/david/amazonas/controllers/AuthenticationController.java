package com.david.amazonas.controllers;

import com.david.amazonas.domains.users.User;
import com.david.amazonas.dtos.AuthenticationDTO;
import com.david.amazonas.dtos.LoginResponseDTO;
import com.david.amazonas.dtos.RegisterDTO;
import com.david.amazonas.infra.security.TokenService;
import com.david.amazonas.repositories.users.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByEmail(data.getLogin()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User user = new User();

        user.setEmail(data.getLogin());
        user.setPassword(encryptedPassword);
        user.setName(data.getName());
        user.setAge(data.getAge());
        user.setAddress(data.getAddress());
        user.setNumber(data.getNumber());
        user.setBirthDate(data.getBirthDate());
        user.setImgUrl(data.getImgUrl());
        user.setCpf(data.getCpf());
        user.setGender(data.getGender());
        user.setUserRole(data.getUserRole());

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
