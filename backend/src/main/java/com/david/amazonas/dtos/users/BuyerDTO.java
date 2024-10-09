package com.david.amazonas.dtos.users;

import com.david.amazonas.domains.users.Buyer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyerDTO {

    private Long buyerId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Integer age;
    private String cpf;
    private String address;

    public BuyerDTO (Buyer buyer) {
        buyerId = buyer.getBuyerId();
        email = buyer.getEmail();
        firstName = buyer.getFirstName();
        lastName = buyer.getLastName();
        phoneNumber = buyer.getPhoneNumber();
        age = buyer.getAge();
        cpf = buyer.getCpf();
        address = buyer.getAddress();
    }
}
