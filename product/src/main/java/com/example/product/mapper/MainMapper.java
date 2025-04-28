package com.example.product.mapper;

import com.example.product.dto.ProductDTO;
import com.example.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MainMapper {

    Product toEntity(ProductDTO dto);
    ProductDTO toDTO(Product entity);
}
