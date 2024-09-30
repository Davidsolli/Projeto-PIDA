package com.david.amazonas.domains.orders;

import com.david.amazonas.domains.payments.Payment;
import com.david.amazonas.domains.products.Product;
import com.david.amazonas.domains.users.Buyer;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Instant orderDate;
    private Double totalAmount;
    private OrderStatus status;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @Getter
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    @Getter
    @Setter
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public List<Product> getProducts() {
        return items.stream().map(OrderItem::getProduct).toList();
    }
}
