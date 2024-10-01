package com.david.amazonas.dtos.users;

import com.david.amazonas.domains.users.Seller;
import lombok.Getter;

@Getter
public class SellerDTO {

    private Long sellerId;
    private String email;
    private String businessName;
    private String cnpj;
    private String phoneNumber;

    public SellerDTO(Long sellerId, String email, String businessName, String cnpj, String phoneNumber) {
        this.sellerId = sellerId;
        this.email = email;
        this.businessName = businessName;
        this.cnpj = cnpj;
        this.phoneNumber = phoneNumber;
    }

    public SellerDTO(Seller seller) {
        sellerId = seller.getSellerId();
        email = seller.getEmail();
        businessName = seller.getBusinessName();
        cnpj = seller.getCnpj();
        phoneNumber = seller.getPhoneNumber();
    }
}
