package com.david.amazonas.dtos;

import com.david.amazonas.domains.users.GenderRole;
import com.david.amazonas.domains.users.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterDTO {

    private String login;
    private String password;
    private String name;
    private UserRole userRole;
}
