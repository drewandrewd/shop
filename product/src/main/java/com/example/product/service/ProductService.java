package com.example.product.service;

import com.example.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public ProductDTO getById(Long id);
    public List<ProductDTO> getAllProducts();
    public ProductDTO saveProduct(ProductDTO productDTO);
    public ProductDTO updateProduct(Long id, ProductDTO productDTO);
    public void deleteProduct(Long id);
}
