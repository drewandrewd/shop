package com.example.product.service;

import com.example.product.dto.BrandDTO;

import java.util.List;

public interface BrandService {

    BrandDTO getById(Long id);
    List<BrandDTO> getAllBrands();
    BrandDTO saveBrand(BrandDTO brandDTO);
    BrandDTO updateBrand(Long id, BrandDTO brandDTO);
    void deleteBrand(Long id);
}
