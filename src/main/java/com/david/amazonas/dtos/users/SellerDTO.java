package com.david.amazonas.dtos.users;

import com.david.amazonas.domains.users.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SellerDTO {

    private Long sellerId;
    private String email;
    private String businessName;
    private String cnpj;
    private String phoneNumber;

    public SellerDTO(Seller seller) {
        sellerId = seller.getSellerId();
        email = seller.getEmail();
        businessName = seller.getBusinessName();
        cnpj = seller.getCnpj();
        phoneNumber = seller.getPhoneNumber();
    }
}
