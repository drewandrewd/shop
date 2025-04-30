package com.example.product.service;

import com.example.product.dto.BrandDTO;
import com.example.product.entity.Brand;
import com.example.product.exception.BrandNotFoundException;
import com.example.product.mapper.MainMapper;
import com.example.product.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;
    private final MainMapper mapper;

    @Override
    public BrandDTO getById(Long id) {
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new BrandNotFoundException(id));
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        List<Brand> list = repository.findAll();
        if (list.isEmpty()) {
            throw new BrandNotFoundException();
        }
        return list.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BrandDTO saveBrand(BrandDTO brandDTO) {
        Brand brand = mapper.toEntity(brandDTO);
        return mapper.toDTO(repository.save(brand));
    }

    @Override
    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        if (!repository.existsById(id)) {
            throw new BrandNotFoundException(id);
        }
        Brand brand = mapper.toEntity(brandDTO);
        brand.setId(id);
        return mapper.toDTO(repository.save(brand));
    }

    @Override
    public void deleteBrand(Long id) {
        if (!repository.existsById(id)) {
            throw new BrandNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
