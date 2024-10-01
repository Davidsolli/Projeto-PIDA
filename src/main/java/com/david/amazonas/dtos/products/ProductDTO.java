package com.david.amazonas.dtos.products;

import com.david.amazonas.domains.products.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {

    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Long sellerId;

    public ProductDTO(Product product) {
        productId = product.getProductId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        sellerId = product.getSeller().getSellerId();
    }
}
