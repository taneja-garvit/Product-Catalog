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

    //  CRUD OPERATIONS

    //fetch
    public List<Product> getallProducts(){
        return productRepository.findAll();  
    }

    //post
    public Product saveProduct(Product product){
        return productRepository.save(product); 
    }

    public Product getProductById(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);

        return optionalProduct.orElse(null);
    }

    
     public boolean deleteProduct(Long id) {
        try {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id); 
                return true;  
            }
            return false;  
        } catch (Exception e) {
            return false; 
        }
    }

     
    public Map<String, Object> searchProductsByCategory(String category) {
        long startTime = System.currentTimeMillis(); 
        
        List<Product> products = productRepository.findByCategory(category);
        
        long endTime = System.currentTimeMillis();
        
        Map<String, Object> result = new HashMap<>(); 
        result.put("products", products);                   
        result.put("executionTime", endTime - startTime);    
        result.put("count", products.size());                
        result.put("indexUsed", "idx_category");             
        
        return result; 
    }


    public Map<String, Object> searchProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        long startTime = System.currentTimeMillis();
        
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        
        long endTime = System.currentTimeMillis();
        
        Map<String, Object> result = new HashMap<>();
        result.put("products", products);
        result.put("executionTime", endTime - startTime);
        result.put("count", products.size());
        result.put("priceRange", "₹" + minPrice + " - ₹" + maxPrice);  
        result.put("indexUsed", "idx_price");
        
        return result;
    }
}
