package com.david.amazonas.dtos.orders;

import com.david.amazonas.domains.orders.Order;
import com.david.amazonas.domains.orders.OrderItem;
import com.david.amazonas.domains.orders.OrderStatus;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {

    private Long orderId;
    private Instant orderDate;
    private OrderStatus status;

    private Long buyerId;

    private Long sellerId;

    private PaymentDTO payment;

    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(PaymentDTO payment, Long buyerId, OrderStatus status, Instant orderDate, Long orderId) {
        this.payment = payment;
        this.buyerId = buyerId;
        this.status = status;
        this.orderDate = orderDate;
        this.orderId = orderId;
    }

    public OrderDTO(Order entity, Long sellerId) {
        orderId = entity.getOrderId();
        orderDate = entity.getOrderDate();
        status = entity.getStatus();
        buyerId = entity.getBuyer().getBuyerId();
        this.sellerId = sellerId;
        payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
        for (OrderItem item: entity.getItems()) {
            OrderItemDTO itemDTO = new OrderItemDTO(item);
            items.add(itemDTO);
        }
    }
}
