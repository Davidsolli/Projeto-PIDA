package com.david.amazonas.repositories.users;

import com.david.amazonas.domains.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    @Query(value = "SELECT obj FROM User obj " +
            "WHERE obj.userRole = com.david.amazonas.domains.users.UserRole.SELLER",
            countQuery = "SELECT COUNT(obj) FROM User obj " +
                    "WHERE obj.userRole = com.david.amazonas.domains.users.UserRole.SELLER")
    Page<User> searchSellers(Pageable pageable);

}
