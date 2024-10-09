package com.david.amazonas.repositories;

import com.david.amazonas.domains.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value =
            "SELECT obj FROM Product obj " +
            "JOIN FETCH obj.seller WHERE :sellerId = obj.seller.sellerId",
            countQuery =
                    "SELECT COUNT(obj) FROM Product obj " +
                    "JOIN obj.seller WHERE :sellerId = obj.seller.sellerId"
    )
    Page<Product> searchBySellerId(Pageable pageable, Long sellerId);
}
