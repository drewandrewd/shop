package com.example.product.service;

import com.example.product.dto.BrandDTO;
import com.example.product.dto.CategoryDTO;
import com.example.product.dto.ProductDTO;
import com.example.product.entity.Brand;
import com.example.product.entity.Category;
import com.example.product.entity.Product;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.mapper.MainMapper;
import com.example.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private MainMapper mapper;

    private ProductService service;

    private final Long ID = 1L;
    private final Category category = new Category("T-Shirts");
    private final Brand brand = new Brand("Nike");
    private final CategoryDTO categoryDTO = new CategoryDTO(ID, "T-Shirts");
    private final BrandDTO brandDTO = new BrandDTO(ID, "Nike");

    private Product getProduct() {
        Category tshirts = new Category("T-Shirts");
        Brand nike = new Brand("Nike");
        return new Product(
                "Sport T-Shirt", "Легкая и удобная", new BigDecimal("2999.99"), 50, tshirts, nike
        );
    }

    private ProductDTO getProductDTO() {
        return new ProductDTO(ID, "Футболка", "Опис", new BigDecimal("1200.00"), 10, categoryDTO, brandDTO);
    }

    @BeforeEach
    void setUp() {
        service = new ProductServiceImpl(repository, mapper);
    }

    @Test
    void shouldReturnProductById() {
        Product product = getProduct();
        ProductDTO dto = getProductDTO();
        when(repository.findById(ID)).thenReturn(Optional.of(product));
        when(mapper.toDTO(product)).thenReturn(dto);
        ProductDTO result = service.getById(ID);
        assertEquals(dto, result);
        verify(repository).findById(ID);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFoundById() {
        when(repository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> service.getById(ID));
        verify(repository).findById(ID);
    }

    @Test
    void shouldReturnAllProducts() {
        Product product = getProduct();
        ProductDTO dto = getProductDTO();
        when(repository.findAll()).thenReturn(List.of(product));
        when(mapper.toDTO(product)).thenReturn(dto);
        List<ProductDTO> result = service.getAllProducts();
        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void shouldThrowWhenNoProductsFound() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(ProductNotFoundException.class, () -> service.getAllProducts());
    }

    @Test
    void shouldSaveProduct() {
        Product product = getProduct();
        ProductDTO dto = getProductDTO();
        when(mapper.toEntity(dto)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);
        when(mapper.toDTO(product)).thenReturn(dto);
        ProductDTO result = service.saveProduct(dto);
        assertEquals(dto, result);
        verify(repository).save(product);
    }

    @Test
    void shouldUpdateProduct() {
        Product product = getProduct();
        ProductDTO dto = getProductDTO();
        when(repository.existsById(ID)).thenReturn(true);
        when(mapper.toEntity(dto)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);
        when(mapper.toDTO(product)).thenReturn(dto);
        ProductDTO result = service.updateProduct(ID, dto);
        assertEquals(dto, result);
        verify(repository).save(product);
    }

    @Test
    void shouldFindByName() {
        Product product = getProduct();
        ProductDTO dto = getProductDTO();
        when(repository.findByNameContainingIgnoreCase("футболка")).thenReturn(List.of(product));
        when(mapper.toDTO(product)).thenReturn(dto);
        List<ProductDTO> result = service.getProductsByName("футболка");
        assertEquals(1, result.size());
    }
}
