package com.example.order.dto;

import com.example.order.entity.Order;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemDTO {

    private Long productId;
    private Long quantity;
    private BigDecimal price;
    private Order order;
}
