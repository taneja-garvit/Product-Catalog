package com.example.demo.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // BASIC CRUD OPERATIONS

    //fetch
    public List<Product> getallProducts(){
        return productRepository.findAll();  // Returns List<Product>
    }

    //post
    public Product saveProduct(Product product){
        return productRepository.save(product);  // Returns saved Product with generated ID
    }

    //fetchbyid
    public Product getProductById(Long id){
         // findById returns Optional<Product> (safe way to handle nulls in Java)
        Optional<Product> optionalProduct = productRepository.findById(id);

        return optionalProduct.orElse(null);
    }

    //delete
     public boolean deleteProduct(Long id) {
        try {
            // Check if product exists first
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);  // Delete from database
                return true;  // Success
            }
            return false;  // Product didn't exist
        } catch (Exception e) {
            return false;  // Error occurred during deletion
        }
    }

      // Method 5: Search products by category (USES INDEX!)
    // This method demonstrates the speed benefit of database indexing
    public Map<String, Object> searchProductsByCategory(String category) {
        // Record start time to measure query performance
        long startTime = System.currentTimeMillis();  // Like Date.now() in JavaScript
        
        // Execute the database query (uses idx_category index for fast search)
        List<Product> products = productRepository.findByCategory(category);
        
        // Record end time to calculate execution duration
        long endTime = System.currentTimeMillis();
        
        // Create structured response with products AND performance metrics
        Map<String, Object> result = new HashMap<>();  // Like {} object in JavaScript
        result.put("products", products);                    // The actual products found
        result.put("executionTime", endTime - startTime);    // How long query took (milliseconds)
        result.put("count", products.size());                // Number of products found
        result.put("indexUsed", "idx_category");             // Which index was used
        
        return result;  // Return structured response for API
    }


    // Method 6: Search products by price range (USES PRICE INDEX!)
    public Map<String, Object> searchProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        // Performance measurement start
        long startTime = System.currentTimeMillis();
        
        // Database query using price index for fast range search
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        
        // Performance measurement end
        long endTime = System.currentTimeMillis();
        
        // Structured response with performance metrics
        Map<String, Object> result = new HashMap<>();
        result.put("products", products);
        result.put("executionTime", endTime - startTime);
        result.put("count", products.size());
        result.put("priceRange", "₹" + minPrice + " - ₹" + maxPrice);  // User-friendly price range
        result.put("indexUsed", "idx_price");
        
        return result;
    }
}
