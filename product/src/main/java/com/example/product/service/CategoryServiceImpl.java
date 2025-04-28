package com.example.product.service;

import com.example.product.dto.CategoryDTO;
import com.example.product.entity.Category;
import com.example.product.exception.CategoryNotFoundException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.mapper.MainMapper;
import com.example.product.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final MainMapper mapper;

    @Override
    public CategoryDTO getById(Long id) {
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> list = repository.findAll();
        if (list.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return list.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = mapper.toEntity(categoryDTO);
        return mapper.toDTO(repository.save(category));
    }

    @Transactional
    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        if (!repository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        Category category = mapper.toEntity(categoryDTO);
        category.setId(id);
        return mapper.toDTO(repository.save(category));
    }

    @Transactional
    @Override
    public void deleteCategory(Long id) {
        if (!repository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
