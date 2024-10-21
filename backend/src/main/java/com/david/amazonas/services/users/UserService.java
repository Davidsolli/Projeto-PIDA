package com.david.amazonas.services.users;

import com.david.amazonas.domains.users.User;
import com.david.amazonas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String getAuthenticatedUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername(); // Retorna o email do usuário
        }
        throw new RuntimeException("Usuário autenticado não encontrado");
    }

    public User getAuthenticatedUser() {
        String email = getAuthenticatedUserEmail();
        return userRepository.findUserByEmail(email).orElseThrow();
    }
}
