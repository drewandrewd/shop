package com.example.product.mapper;

import com.example.product.dto.BrandDTO;
import com.example.product.dto.CategoryDTO;
import com.example.product.dto.ProductDTO;
import com.example.product.entity.Brand;
import com.example.product.entity.Category;
import com.example.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MainMapper {

    Product toEntity(ProductDTO dto);
    ProductDTO toDTO(Product entity);

    Category toEntity(CategoryDTO dto);
    CategoryDTO toDTO(Category entity);

    Brand toEntity(BrandDTO dto);
    BrandDTO toDTO(Brand entity);
}
