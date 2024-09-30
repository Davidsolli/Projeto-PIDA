package com.david.amazonas.domains.users;

import com.david.amazonas.domains.orders.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyerId;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Integer age;
    @Column(unique = true)
    private String cpf;
    private String address;

    @OneToMany(mappedBy = "buyer")
    private List<Order> orders = new ArrayList<>();
}
