package com.example.product.service;

import com.example.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO getById(Long id);
    List<ProductDTO> getAllProducts();
    ProductDTO saveProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
