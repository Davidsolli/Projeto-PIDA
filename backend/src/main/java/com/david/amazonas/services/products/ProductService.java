package com.david.amazonas.services.products;

import com.david.amazonas.domains.products.Product;
import com.david.amazonas.dtos.products.ProductDTO;
import com.david.amazonas.repositories.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        Product product = new Product();
        copyDtoToEntity(product, productDTO);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return new ProductDTO(product);

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
