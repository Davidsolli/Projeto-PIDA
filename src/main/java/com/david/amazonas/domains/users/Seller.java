package com.david.amazonas.domains.users;

import com.david.amazonas.domains.products.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;
    @Column(unique = true)
    private String email;
    private String password;
    private String businessName;
    @Column(unique = true)
    private String cnpj;
    private String phoneNumber;

    @OneToMany(mappedBy = "seller")
    private List<Product> products = new ArrayList<>();
}
