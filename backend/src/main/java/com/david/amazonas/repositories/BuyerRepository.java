package com.david.amazonas.repositories;

import com.david.amazonas.domains.users.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
