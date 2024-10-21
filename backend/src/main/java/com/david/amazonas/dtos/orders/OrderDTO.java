package com.david.amazonas.dtos.orders;

import com.david.amazonas.domains.orders.Order;
import com.david.amazonas.domains.orders.OrderStatus;
import com.david.amazonas.dtos.payments.PaymentDTO;
import com.david.amazonas.dtos.products.ProductMinDTO;
import com.david.amazonas.dtos.users.BuyerDTO;
import com.david.amazonas.dtos.users.SellerDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private Instant moment;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private ProductMinDTO product;
    private SellerDTO seller;
    private BuyerDTO buyer;
    private PaymentDTO payment;

    public OrderDTO(Order order) {
        id = order.getId();
        moment = order.getMoment();
        status = order.getStatus();
        seller = new SellerDTO(order.getSeller());
        buyer = new BuyerDTO(order.getBuyer());
        product = new ProductMinDTO(order.getProduct());
        payment = order.getPayment() != null ? new PaymentDTO(order.getPayment()) : null;
    }
}
