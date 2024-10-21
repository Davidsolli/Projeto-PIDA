package com.david.amazonas.services.users;

import com.david.amazonas.domains.users.User;
import com.david.amazonas.dtos.users.UserDTO;
import com.david.amazonas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String getAuthenticatedUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) return userDetails.getUsername();
        throw new RuntimeException("Usuário autenticado não encontrado");
    }

    public User getAuthenticatedUser() {
        String email = getAuthenticatedUserEmail();
        return userRepository.findUserByEmail(email).orElseThrow();
    }

    @Transactional(readOnly = true)
    public UserDTO findUser() {
        User user = getAuthenticatedUser();
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO update(UserDTO userDTO) {

        User user = getAuthenticatedUser();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        user.setAddress(userDTO.getAddress());
        user.setNumber(userDTO.getNumber());
        user.setBirthDate(userDTO.getBirthDate());
        user.setImgUrl(userDTO.getImgUrl());
        user.setCpf(userDTO.getCpf());
        user.setGender(userDTO.getGender());
        user.setUserRole(userDTO.getUserRole());

        user = userRepository.save(user);

        return new UserDTO(user);
    }
}
