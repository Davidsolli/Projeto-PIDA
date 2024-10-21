package com.david.amazonas.repositories;

import com.david.amazonas.domains.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT obj FROM Product obj JOIN FETCH obj.seller " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :productName, '%')) " +
            "AND obj.seller.id = :sellerId",
            countQuery = "SELECT COUNT(obj) FROM Product obj JOIN obj.seller " +
                    "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :productName, '%')) " +
                    "AND obj.seller.id = :sellerId"
    )
    Page<Product> searchFilteredProductsBySellerName(
            String productName,
            Long sellerId,
            Pageable pageable
    );
}
