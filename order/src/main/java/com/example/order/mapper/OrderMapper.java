package com.example.order.mapper;

import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderItemDTO;
import com.example.order.dto.ProductDTO;
import com.example.order.entity.Order;
import com.example.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", constant = "NEW")
    Order toEntity(OrderDTO dto);

    List<OrderItem> toEntityItems(List<OrderItemDTO> dtos);

    @Mapping(source = "id", target = "orderId")
    OrderDTO toDTO(Order order);

    List<OrderItemDTO> toDTOItems(List<OrderItem> items);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "price")
    @Mapping(source = "product.description", target = "description")
    @Mapping(source = "item.quantity", target = "quantity")
    OrderItemDTO toOrderItemDTO(OrderItem item, ProductDTO product);
}
