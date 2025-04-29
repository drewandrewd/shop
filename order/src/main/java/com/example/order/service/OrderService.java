package com.example.order.service;

import com.example.order.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO getById(Long id);
    List<OrderDTO> getAllOrdersByUserId(Long id);
    OrderDTO createOrder(OrderDTO request);
}
