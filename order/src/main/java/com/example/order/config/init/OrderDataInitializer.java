package com.example.order.config.init;

import com.example.order.client.ProductServiceClient;
import com.example.order.dto.ProductDTO;
import com.example.order.entity.Order;
import com.example.order.entity.OrderItem;
import com.example.order.repostory.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderDataInitializer implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final ProductServiceClient productClient;

    @Override
    public void run(String... args) {
        if (orderRepository.count() == 0) {
            Order order = new Order();
            order.setUserId(1L);
            order.setCreatedAt(LocalDateTime.now());
            order.setStatus("CREATED");

            List<OrderItem> items = Arrays.asList(
                    createItem(1L, 2, order),
                    createItem(2L, 1, order),
                    createItem(3L, 3, order)
            );

            order.setItems(items);
            orderRepository.save(order);
        }
    }

    private OrderItem createItem(Long productId, int quantity, Order order) {
        ProductDTO product = productClient.getProductById(productId);
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProductId(product.getId());
        item.setProductName(product.getName());
        item.setPrice(product.getPrice());
        item.setDescription(product.getDescription());
        item.setQuantity(quantity);
        return item;
    }
}
