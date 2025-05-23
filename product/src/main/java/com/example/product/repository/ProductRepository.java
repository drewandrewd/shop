package com.example.product.repository;

import com.example.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT c FROM Product c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT c FROM Product c WHERE LOWER(c.brand.name) LIKE LOWER(CONCAT('%', :brand, '%'))")
    List<Product> findByBrandNameContainingIgnoreCase(@Param("brand") String brand);
}
