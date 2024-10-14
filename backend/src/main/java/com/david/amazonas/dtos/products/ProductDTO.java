package com.david.amazonas.dtos.products;

import com.david.amazonas.domains.products.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String imgUrl;

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        quantity = product.getQuantity();
        imgUrl = product.getImgUrl();
    }
}
