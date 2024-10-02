package com.david.amazonas.repositories;

import com.david.amazonas.domains.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /*@Query( TODO )*/
    //Page<ProductDTO> searchBySellerId(Pageable pageable, Long sellerId);
}
