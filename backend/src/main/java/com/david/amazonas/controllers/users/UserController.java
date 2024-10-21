package com.david.amazonas.controllers.users;

import com.david.amazonas.dtos.users.UserDTO;
import com.david.amazonas.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> findUser() {
        UserDTO userDTO = userService.findUser();
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        userDTO = userService.update(userDTO);
        return ResponseEntity.ok(userDTO);
    }
}
