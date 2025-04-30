package com.example.order.client;

import com.example.order.dto.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class ProductServiceClient {

    private final RestTemplate restTemplate;

    public ProductDTO getProductById(Long productId) {
        return restTemplate.getForObject("http://product-service/products/" + productId, ProductDTO.class);
    }

    public void decreaseProductQuantity(Long productId, Integer quantity) {
        restTemplate.postForLocation("http://product-service/products/" + productId + "/decrease?quantity=" + quantity, null);
    }
}
