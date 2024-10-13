package com.david.amazonas.repositories;

import com.david.amazonas.domains.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    UserDetails findByEmail(String email);
}
