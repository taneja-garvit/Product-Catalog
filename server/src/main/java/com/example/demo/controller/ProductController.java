package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getallProducts() {
        return productService.getallProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) { // @RequestBody converts JSON to
                                                                                 // Product object
        try {
            Product savedProduct = productService.saveProduct(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if(product!=null){
            return ResponseEntity.ok(product);
        }
        else{
            return ResponseEntity.notFound().build();
        }

    } // @PathVariable extracts {id} from URL

     @PutMapping("/{id}")  // HTTP PUT method with ID parameter
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product){
        Product existingProduct = productService.getProductById(id);

         if (existingProduct == null) {
            // Product doesn't exist - return 404 NOT FOUND
            return ResponseEntity.notFound().build();
        }
        
        // Set the ID to ensure we're updating the correct product
        product.setId(id);
        
        try {
            // Save updated product
            Product updatedProduct = productService.saveProduct(product);
            
            // Return 200 OK with updated product
            // Like: res.status(200).json(updatedProduct) in Express.js
            return ResponseEntity.ok(updatedProduct);
            
        } catch (Exception e) {
            // Error occurred - return 400 BAD REQUEST
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")  // HTTP DELETE method
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        
        try {
            // Call service to delete product
            boolean deleted = productService.deleteProduct(id);
            
            if (deleted) {
                // Successfully deleted - return 200 OK with success message
                // Like: res.status(200).json({message: "Deleted successfully"}) in Express.js
                return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
            } else {
                // Product not found - return 404 NOT FOUND
                // Like: res.status(404).json({error: "Product not found"}) in Express.js
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Product not found"));
            }
            
        } catch (Exception e) {
            // Error occurred - return 500 INTERNAL SERVER ERROR
            // Like: res.status(500).json({error: "Server error"}) in Express.js
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Failed to delete product"));
        }
    }


    
@GetMapping("/search/category/{category}")
public Map<String, Object> searchByCategory(@PathVariable String category) {
    return productService.searchProductsByCategory(category);
}

@GetMapping("/search/price")
public Map<String, Object> searchByPriceRange(
        @RequestParam BigDecimal minPrice,
        @RequestParam BigDecimal maxPrice) {
    return productService.searchProductsByPriceRange(minPrice, maxPrice);
}
}
