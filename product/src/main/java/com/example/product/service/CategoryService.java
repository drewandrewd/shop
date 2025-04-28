package com.example.product.service;

import com.example.product.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO getById(Long id);
    List<CategoryDTO> getAllCategories();
    CategoryDTO saveCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}
