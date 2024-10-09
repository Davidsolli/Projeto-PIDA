package com.david.amazonas.dtos.orders;

import com.david.amazonas.domains.orders.OrderItem;
import lombok.Data;

@Data
public class OrderItemDTO {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;

    public OrderItemDTO(OrderItem entity) {
        productId = entity.getProduct().getProductId();
        name = entity.getProduct().getName();
        price = entity.getPrice();
        quantity = entity.getQuantity();
    }
}
