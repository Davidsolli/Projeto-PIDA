package com.david.amazonas.domains.users;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == UserRole.ADMIN) return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_BUYER"),
                new SimpleGrantedAuthority("ROLE_SELLER"));
        else if (this.userRole == UserRole.SELLER) return List.of(new SimpleGrantedAuthority("ROLE_SELLER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_BUYER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

}
