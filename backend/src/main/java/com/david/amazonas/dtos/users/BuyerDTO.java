package com.david.amazonas.dtos.users;

import com.david.amazonas.domains.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyerDTO {

    private Long id;
    private String name;
    private String email;
    private String number;
    private String imgUrl;

    public BuyerDTO(User buyer) {
        id = buyer.getId();
        name = buyer.getName();
        email = buyer.getEmail();
        number = buyer.getNumber();
        imgUrl = buyer.getImgUrl();
    }
}
