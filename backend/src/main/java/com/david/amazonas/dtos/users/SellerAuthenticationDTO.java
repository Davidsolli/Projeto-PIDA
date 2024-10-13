package com.david.amazonas.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellerAuthenticationDTO {

    private String Login;
    private String password;
}
