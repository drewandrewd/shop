package com.example.order.mapper;

import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderItemDTO;
import com.example.order.dto.ProductDTO;
import com.example.order.entity.Order;
import com.example.order.entity.OrderItem;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-30T19:47:39+0500",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setUserId( dto.getUserId() );
        order.setItems( toEntityItems( dto.getItems() ) );

        order.setCreatedAt( java.time.LocalDateTime.now() );
        order.setStatus( "NEW" );

        return order;
    }

    @Override
    public List<OrderItem> toEntityItems(List<OrderItemDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( dtos.size() );
        for ( OrderItemDTO orderItemDTO : dtos ) {
            list.add( orderItemDTOToOrderItem( orderItemDTO ) );
        }

        return list;
    }

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        Long orderId = null;
        Long userId = null;
        LocalDateTime createdAt = null;
        String status = null;
        List<OrderItemDTO> items = null;

        orderId = order.getId();
        userId = order.getUserId();
        createdAt = order.getCreatedAt();
        status = order.getStatus();
        items = toDTOItems( order.getItems() );

        BigDecimal totalPrice = null;

        OrderDTO orderDTO = new OrderDTO( orderId, userId, createdAt, status, items, totalPrice );

        return orderDTO;
    }

    @Override
    public List<OrderItemDTO> toDTOItems(List<OrderItem> items) {
        if ( items == null ) {
            return null;
        }

        List<OrderItemDTO> list = new ArrayList<OrderItemDTO>( items.size() );
        for ( OrderItem orderItem : items ) {
            list.add( orderItemToOrderItemDTO( orderItem ) );
        }

        return list;
    }

    @Override
    public OrderItemDTO toOrderItemDTO(OrderItem item, ProductDTO product) {
        if ( item == null && product == null ) {
            return null;
        }

        Integer quantity = null;
        if ( item != null ) {
            quantity = item.getQuantity();
        }
        Long productId = null;
        String productName = null;
        BigDecimal price = null;
        String description = null;
        if ( product != null ) {
            productId = product.getId();
            productName = product.getName();
            price = product.getPrice();
            description = product.getDescription();
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO( productId, productName, quantity, price, description );

        return orderItemDTO;
    }

    protected OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setProductId( orderItemDTO.getProductId() );
        orderItem.setProductName( orderItemDTO.getProductName() );
        orderItem.setQuantity( orderItemDTO.getQuantity() );
        orderItem.setPrice( orderItemDTO.getPrice() );
        orderItem.setDescription( orderItemDTO.getDescription() );

        return orderItem;
    }

    protected OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        Long productId = null;
        String productName = null;
        Integer quantity = null;
        BigDecimal price = null;
        String description = null;

        productId = orderItem.getProductId();
        productName = orderItem.getProductName();
        quantity = orderItem.getQuantity();
        price = orderItem.getPrice();
        description = orderItem.getDescription();

        OrderItemDTO orderItemDTO = new OrderItemDTO( productId, productName, quantity, price, description );

        return orderItemDTO;
    }
}
