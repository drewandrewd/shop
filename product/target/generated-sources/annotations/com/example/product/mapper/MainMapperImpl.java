package com.example.product.mapper;

import com.example.product.dto.BrandDTO;
import com.example.product.dto.CategoryDTO;
import com.example.product.dto.ProductDTO;
import com.example.product.entity.Brand;
import com.example.product.entity.Category;
import com.example.product.entity.Product;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-30T01:13:10+0500",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class MainMapperImpl implements MainMapper {

    @Override
    public Product toEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String name = null;
        String description = null;
        BigDecimal price = null;
        Integer quantity = null;
        Category category = null;
        Brand brand = null;

        name = dto.getName();
        description = dto.getDescription();
        price = dto.getPrice();
        quantity = dto.getQuantity();
        category = dto.getCategory();
        brand = dto.getBrand();

        Long id = null;

        Product product = new Product( id, name, description, price, quantity, category, brand );

        return product;
    }

    @Override
    public ProductDTO toDTO(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setName( entity.getName() );
        productDTO.setDescription( entity.getDescription() );
        productDTO.setPrice( entity.getPrice() );
        productDTO.setQuantity( entity.getQuantity() );
        productDTO.setCategory( entity.getCategory() );
        productDTO.setBrand( entity.getBrand() );

        return productDTO;
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String name = null;

        name = dto.getName();

        Long id = null;

        Category category = new Category( id, name );

        return category;
    }

    @Override
    public CategoryDTO toDTO(Category entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setName( entity.getName() );

        return categoryDTO;
    }

    @Override
    public Brand toEntity(BrandDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String name = null;

        name = dto.getName();

        Long id = null;

        Brand brand = new Brand( id, name );

        return brand;
    }

    @Override
    public BrandDTO toDTO(Brand entity) {
        if ( entity == null ) {
            return null;
        }

        BrandDTO brandDTO = new BrandDTO();

        brandDTO.setName( entity.getName() );

        return brandDTO;
    }
}
