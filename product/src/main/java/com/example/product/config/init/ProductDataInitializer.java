package com.example.product.config.init;

import com.example.product.entity.Brand;
import com.example.product.entity.Category;
import com.example.product.entity.Product;
import com.example.product.repository.BrandRepository;
import com.example.product.repository.CategoryRepository;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ProductDataInitializer implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (brandRepository.count() == 0 && categoryRepository.count() == 0) {
            Brand nike = brandRepository.save(new Brand("Nike"));
            Brand adidas = brandRepository.save(new Brand("Adidas"));
            Brand uniqlo = brandRepository.save(new Brand("Uniqlo"));

            Category tshirts = categoryRepository.save(new Category("T-Shirts"));
            Category jackets = categoryRepository.save(new Category("Jackets"));
            Category pants = categoryRepository.save(new Category("Pants"));

            productRepository.save(new Product(
                    "Sport T-Shirt", "Легкая и удобная", new BigDecimal("2999.99"), 50, tshirts, nike
                    ));
            productRepository.save(new Product(
                    "Windbreaker Jacket", "Ветрозащитная куртка", new BigDecimal("8999.99"), 25, jackets, adidas
            ));
            productRepository.save(new Product(
                    "Casual Pants", "Повседневные брюки", new BigDecimal("4999.99"), 40, pants, uniqlo
            ));
        }
    }
}
