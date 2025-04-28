package com.example.product.controller;

import com.example.product.dto.BrandDTO;
import com.example.product.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@AllArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/{id}")
    public BrandDTO getById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    @GetMapping
    public Iterable<BrandDTO> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping
    public BrandDTO createBrand(@RequestBody BrandDTO brandDTO) {
        return brandService.saveBrand(brandDTO);
    }

    @PutMapping("/{id}")
    public BrandDTO updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        return brandService.updateBrand(id, brandDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
