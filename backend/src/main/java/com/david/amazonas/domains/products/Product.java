package com.david.amazonas.domains.products;

import com.david.amazonas.domains.orders.Order;
import com.david.amazonas.domains.orders.OrderItem;
import com.david.amazonas.domains.users.Seller;
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
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Getter
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    public List<Order> getOrders() {
        return items.stream().map(OrderItem::getOrder).toList();
    }
}
