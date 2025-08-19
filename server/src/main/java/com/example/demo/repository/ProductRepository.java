package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Method 1: Find by category (shows single index benefit)
    List<Product> findByCategory(String category);
    
    // Method 2: Find by price range (shows price index benefit)
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    // Method 3: Combined search (shows composite index benefit)
    List<Product> findByCategoryAndPriceBetween(String category, 
                                                BigDecimal minPrice, 
                                                BigDecimal maxPrice);
}







