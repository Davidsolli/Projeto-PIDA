package com.david.amazonas.dtos.users;

import com.david.amazonas.domains.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SellerListDTO {

    private String name;
    private String email;
    private String number;
    private String imgUrl;

    public SellerListDTO(User seller) {
        name = seller.getName();
        email = seller.getEmail();
        number = seller.getNumber();
        imgUrl = seller.getImgUrl();
    }
}
