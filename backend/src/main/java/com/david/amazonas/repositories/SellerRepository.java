package com.david.amazonas.repositories;

import com.david.amazonas.domains.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
