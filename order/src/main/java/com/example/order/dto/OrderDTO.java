package com.example.order.dto;

import com.example.order.entity.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {

    private Long orderId;
    private Long userId;
    private LocalDateTime createdAt;
    private String status;
    private List<OrderItemDTO> items;
    private BigDecimal totalPrice;
}
