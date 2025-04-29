package com.example.order.service;

import com.example.order.client.AuthServiceClient;
import com.example.order.client.ProductServiceClient;
import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderItemDTO;
import com.example.order.dto.ProductDTO;
import com.example.order.entity.Order;
import com.example.order.entity.OrderItem;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.exception.OrderWrongQuantityException;
import com.example.order.mapper.OrderMapper;
import com.example.order.repostory.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductServiceClient productClient;
    private final AuthServiceClient authClient;
    private final OrderMapper orderMapper;

    @Override
    public OrderDTO getById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDTO).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public List<OrderDTO> getAllOrdersByUserId(Long id) {
        List<Order> list = orderRepository.findByUserId(id);
        if (list.isEmpty()) {
            throw new OrderNotFoundException();
        }
        return list.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        authClient.userExist(dto.getUserId());
        Order order = orderMapper.toEntity(dto);
        List<OrderItem> items = dto.getItems().stream().map(i -> {
            ProductDTO product = productClient.getProductById(i.getProductId());
            if (product.getQuantity() < i.getQuantity()) {
                throw new OrderWrongQuantityException();
            }
            productClient.decreaseProductQuantity(product.getId(), i.getQuantity());
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductId(i.getProductId());
            item.setQuantity(i.getQuantity());
            return item;
        }).toList();
        order.setItems(items);
        Order saved = orderRepository.save(order);
        OrderDTO response = mapToDTO(saved);
        return response;
    }

    private OrderDTO mapToDTO(Order order) {
        List<OrderItemDTO> itemDTOs = order.getItems().stream().map(item -> {
            ProductDTO product = productClient.getProductById(item.getProductId());
            return orderMapper.toOrderItemDTO(item, product);
        }).toList();

        BigDecimal totalPrice = itemDTOs.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        OrderDTO dto = orderMapper.toDTO(order);
        dto.setItems(itemDTOs);
        dto.setTotalPrice(totalPrice);
        return dto;
    }
}
