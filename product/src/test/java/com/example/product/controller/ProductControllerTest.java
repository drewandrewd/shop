package com.example.product.controller;

import com.example.product.dto.BrandDTO;
import com.example.product.dto.CategoryDTO;
import com.example.product.dto.ProductDTO;
import com.example.product.entity.Brand;
import com.example.product.entity.Category;
import com.example.product.entity.Product;
import com.example.product.integration.PostgresContainer;
import com.example.product.repository.BrandRepository;
import com.example.product.repository.CategoryRepository;
import com.example.product.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ProductControllerTest extends PostgresContainer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Long savedProductId;

    private final Category category = new Category("T-Shirts");
    private final Brand brand = new Brand("Nike");
    private final CategoryDTO categoryDTO = new CategoryDTO(1l,"T-Shirts");
    private final BrandDTO brandDTO = new BrandDTO(1L,"Nike");

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        brandRepository.save(brand);
        categoryRepository.save(category);
        Product product = repository.save(getProduct());
        savedProductId = product.getId();
    }

    private Product getProduct() {
        return new Product("Футболка", "Легкая и удобная", new BigDecimal("2999.99"), 50, category, brand);
    }

    private ProductDTO getProductDTO() {
        return new ProductDTO(null, "Футболка", "Легкая и удобная", new BigDecimal("2999.99"), 50, categoryDTO, brandDTO);
    }

    @Test
    void shouldReturnAllProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Футболка"))
                .andExpect(jsonPath("$[0].brand.name").value("Nike"));
    }

    @Test
    void shouldReturnProductById() throws Exception {
        mockMvc.perform(get("/products/" + savedProductId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Футболка"))
                .andExpect(jsonPath("$.description").value("Легкая и удобная"));
    }

    @Test
    void shouldCreateProduct() throws Exception {
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getProductDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Футболка"))
                .andExpect(jsonPath("$.brand.name").value("Nike"));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        ProductDTO updated = getProductDTO();
        updated.setName("Обновлённая футболка");

        mockMvc.perform(put("/products/" + savedProductId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Обновлённая футболка"));
    }

    @Test
    void shouldDecreaseQuantity() throws Exception {
        mockMvc.perform(post("/products/" + savedProductId + "/decrease")
                        .param("quantity", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(45));
    }


    @Test
    void shouldSearchByName() throws Exception {
        mockMvc.perform(get("/products/search/by-name")
                        .param("name", "футболка"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Футболка"));
    }

    @Test
    void shouldSearchByBrand() throws Exception {
        mockMvc.perform(get("/products/search/by-brand")
                        .param("brand", "nike"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].brand.name").value("Nike"));
    }

    @Test
    void shouldReturnNotFoundWhenProductDoesNotExist() throws Exception {
        mockMvc.perform(get("/products/99999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Product with id 99999 is not found"));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        mockMvc.perform(delete("/products/" + savedProductId))
                .andExpect(status().isNoContent());
    }
}
