package com.david.amazonas.dtos.users;

import com.david.amazonas.domains.users.GenderRole;
import com.david.amazonas.domains.users.User;
import com.david.amazonas.domains.users.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String address;
    private String number;
    private String birthDate;
    private String imgUrl;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private GenderRole gender;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public UserDTO(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        age = user.getAge();
        address = user.getAddress();
        number = user.getNumber();
        birthDate = user.getBirthDate();
        imgUrl = user.getImgUrl();
        cpf = user.getCpf();
        gender = user.getGender();
        userRole = user.getUserRole();
    }
}
