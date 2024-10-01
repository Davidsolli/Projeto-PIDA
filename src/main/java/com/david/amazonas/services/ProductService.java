package com.david.amazonas.services;

import com.david.amazonas.domains.products.Product;
import com.david.amazonas.domains.users.Seller;
import com.david.amazonas.dtos.products.ProductDTO;
import com.david.amazonas.repositories.ProductRepository;
import com.david.amazonas.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = new Product();
        Seller seller = sellerRepository.getReferenceById(productDTO.getSellerId());

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setSeller(seller);

        product = productRepository.save(product);

        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return new ProductDTO(product);
    }
}
