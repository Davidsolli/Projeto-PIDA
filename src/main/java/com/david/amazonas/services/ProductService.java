package com.david.amazonas.services;

import com.david.amazonas.domains.products.Product;
import com.david.amazonas.dtos.products.ProductDTO;
import com.david.amazonas.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCreatedAt(productDTO.getCreatedAt());

        product = productRepository.save(product);

        return new ProductDTO(product);
    }
}
