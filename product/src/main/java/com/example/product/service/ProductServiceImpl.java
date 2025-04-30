package com.example.product.service;

import com.example.product.dto.ProductDTO;
import com.example.product.entity.Product;
import com.example.product.exception.ProductLowerZeroQuantityException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.mapper.MainMapper;
import com.example.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final MainMapper mapper;

    @Override
    public ProductDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = repository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return products.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO);
        return mapper.toDTO(repository.save(product));
    }

    @Transactional
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        Product productToUpdate = mapper.toEntity(productDTO);
        productToUpdate.setId(id);
        return mapper.toDTO(repository.save(productToUpdate));
    }

    @Transactional
    @Override
    public ProductDTO updateProductQuantity(Long id, Integer quantity) {
        Product productToUpdate = repository
                .findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        if (productToUpdate.getQuantity() - quantity < 0) {
            throw new ProductLowerZeroQuantityException();
        }
        productToUpdate.setQuantity(productToUpdate.getQuantity() - quantity);
        return mapper.toDTO(repository.save(productToUpdate));
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        if (repository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
