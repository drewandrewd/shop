package com.example.product.mapper;

import com.example.product.dto.BrandDTO;
import com.example.product.dto.CategoryDTO;
import com.example.product.dto.ProductDTO;
import com.example.product.entity.Brand;
import com.example.product.entity.Category;
import com.example.product.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-30T16:05:48+0500",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class MainMapperImpl implements MainMapper {

    @Override
    public Product toEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( dto.getId() );
        product.setName( dto.getName() );
        product.setDescription( dto.getDescription() );
        product.setPrice( dto.getPrice() );
        product.setQuantity( dto.getQuantity() );
        product.setCategory( dto.getCategory() );
        product.setBrand( dto.getBrand() );

        return product;
    }

    @Override
    public ProductDTO toDTO(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( entity.getId() );
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

        Category category = new Category();

        category.setName( dto.getName() );

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

        Brand brand = new Brand();

        brand.setName( dto.getName() );

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
