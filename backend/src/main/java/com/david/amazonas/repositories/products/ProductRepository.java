package com.david.amazonas.repositories.products;

import com.david.amazonas.domains.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
