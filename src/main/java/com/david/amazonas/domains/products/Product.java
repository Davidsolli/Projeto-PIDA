package com.david.amazonas.domains.products;

import com.david.amazonas.domains.orders.OrderItem;
import com.david.amazonas.domains.users.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();
}
