package com.david.amazonas.domains.users;

import com.david.amazonas.domains.products.Product;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sellers")
public class Seller implements UserDetails {

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
    private SellerRole role;

    @OneToMany(mappedBy = "seller")
    private List<Product> products = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == SellerRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_SELLER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_SELLER"));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
