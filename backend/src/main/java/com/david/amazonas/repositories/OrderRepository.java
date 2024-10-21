package com.david.amazonas.repositories;

import com.david.amazonas.domains.orders.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(
            value = "SELECT o FROM Order o JOIN FETCH o.seller JOIN FETCH o.buyer LEFT JOIN FETCH o.payment WHERE o.seller.id = :sellerId",
            countQuery = "SELECT COUNT(o) FROM Order o WHERE o.seller.id = :sellerId"
    )
    Page<Order> findAllOrdersBySellerId(Long sellerId, Pageable pageable);

    @Query(
            value = "SELECT o FROM Order o JOIN FETCH o.seller JOIN FETCH o.buyer LEFT JOIN FETCH o.payment WHERE o.buyer.id = :buyerId",
            countQuery = "SELECT COUNT(o) FROM Order o WHERE o.buyer.id = :buyerId"
    )
    Page<Order> findAllOrdersByBuyerId(Long buyerId, Pageable pageable);
}
