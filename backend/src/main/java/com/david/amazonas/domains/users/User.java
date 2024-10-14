package com.david.amazonas.domains.users;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private Integer age;
    private String password;
    private String address;
    private String number;
    private String birthDate;
    private String imgUrl;
    private String cpf;
    private GenderRole gender;
    private UserRole userRole;
}
