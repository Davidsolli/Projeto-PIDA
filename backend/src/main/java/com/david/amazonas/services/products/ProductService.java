package com.david.amazonas.services.products;

import com.david.amazonas.domains.products.Product;
import com.david.amazonas.domains.users.User;
import com.david.amazonas.dtos.products.ProductDTO;
import com.david.amazonas.repositories.ProductRepository;
import com.david.amazonas.repositories.UserRepository;
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
    private UserRepository userRepository;

    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        Product product = new Product();
        User seller = userRepository.findById(productDTO.getSellerId()).orElseThrow();
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
    public Page<ProductDTO> findAll(String productName, Long sellerId, Pageable pageable) {
        Page<Product> result = productRepository.searchFilteredProductsBySellerName(productName, sellerId, pageable);
        return result.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productRepository.getReferenceById(id);
        copyDtoToEntity(product, productDTO);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional()
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private void copyDtoToEntity(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImgUrl(productDTO.getImgUrl());
    }
}
