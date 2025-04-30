package com.example.product.controller;

import com.example.product.dto.ProductDTO;
import com.example.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping
    public Iterable<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @PostMapping("/{id}/decrease")
    public ProductDTO updateProductQuantity(@PathVariable Long id, @RequestParam("quantity") Integer qty) {
        return productService.updateProductQuantity(id, qty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/by-name")
    public Iterable<ProductDTO> getProductByName(@RequestParam String name) {
        return productService.getProductsByName(name);
    }

    @GetMapping("/search/by-brand")
    public Iterable<ProductDTO> getProductsByBrand(@RequestParam String brand) {
        return productService.getProductsByBrand(brand);
    }
}
