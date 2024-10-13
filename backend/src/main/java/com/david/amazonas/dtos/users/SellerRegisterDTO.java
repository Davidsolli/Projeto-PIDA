package com.david.amazonas.dtos.users;

import com.david.amazonas.domains.users.SellerRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellerRegisterDTO {

    private String email;
    private String password;
    private SellerRole role;
    private String businessName;
    private String cnpj;
    private String phoneNumber;
}
