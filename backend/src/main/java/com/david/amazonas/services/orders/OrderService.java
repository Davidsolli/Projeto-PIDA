package com.david.amazonas.services.orders;

import com.david.amazonas.domains.orders.Order;
import com.david.amazonas.domains.orders.OrderStatus;
import com.david.amazonas.domains.users.User;
import com.david.amazonas.dtos.orders.OrderDTO;
import com.david.amazonas.repositories.OrderRepository;
import com.david.amazonas.repositories.PaymentRepository;
import com.david.amazonas.repositories.ProductRepository;
import com.david.amazonas.repositories.UserRepository;
import com.david.amazonas.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/* Consertar a degradação na busca */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return new OrderDTO(order);
    }

    @Transactional(readOnly = true)
    public Page<OrderDTO> findAllByBuyerId(Pageable pageable) {
        User user = userService.getAuthenticatedUser();
        Page<Order> result = orderRepository.findAllOrdersByBuyerId(user.getId(), pageable);
        return result.map(OrderDTO::new);
    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User buyer = userService.getAuthenticatedUser();
        order.setBuyer(buyer);

        order.setSeller(userRepository.getReferenceById(orderDTO.getSeller().getId()));

        if (orderDTO.getPayment() != null) {
            order.setPayment(paymentRepository.getReferenceById(orderDTO.getPayment().getId()));
        } else {
            order.setPayment(null);
        }
        order.setProduct(productRepository.getReferenceById(orderDTO.getProduct().getId()));

        order = orderRepository.save(order);

        return new OrderDTO(order);
    }
}
