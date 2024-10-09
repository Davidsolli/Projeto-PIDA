package com.david.amazonas.services;

import com.david.amazonas.domains.products.Product;
import com.david.amazonas.domains.users.Seller;
import com.david.amazonas.dtos.products.ProductDTO;
import com.david.amazonas.repositories.ProductRepository;
import com.david.amazonas.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        copyDtoToEntity(product, productDTO);
        product.setSeller(seller);

        product = productRepository.save(product);

        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Long sellerId, Pageable pageable) {

        Page<Product> productPage = productRepository.searchBySellerId(pageable, sellerId);

        return productPage.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow();
        copyDtoToEntity(product, productDTO);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public void copyDtoToEntity(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
    }
}