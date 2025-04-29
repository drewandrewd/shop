package com.example.order.mapper;

import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderItemDTO;
import com.example.order.entity.Order;
import com.example.order.entity.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);
    List<OrderItemDTO> toOrderItemDTO(List<OrderItem> items);
}
