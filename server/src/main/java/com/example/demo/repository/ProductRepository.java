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










// package com.example.demo.repository;



//     // Method 1: Find products by category (USES INDEX for fast search!)
//     // Method naming convention: findBy + FieldName
//     // Spring converts this to: SELECT * FROM products WHERE category = ?
//     List<Product> findByCategory(String category);


//     // Method 2: Find products by category AND price range (USES COMPOSITE INDEX!)
//     // This uses the compound index we created: idx_category_price
//     // Spring converts to: SELECT * FROM products WHERE category = ? AND price BETWEEN ? AND ?
//     List<Product> findBycafindByCategoryAndPriceBetween(String category, 
//     BigDecimal minPrice, 
//     BigDecimal maxPrice);

//     // Method 3: Find products by price range only (USES PRICE INDEX!)
//     // Spring converts to: SELECT * FROM products WHERE price BETWEEN ? AND ?
//     List<Product> findByPriceBetween(BigDecimal minPrice,  // Minimum price
//                                      BigDecimal maxPrice);

//      // Method 4: Search products by name (partial match, case-insensitive)
//     // Like a search box functionality - finds "iPhone" in "Apple iPhone 14 Pro"
//     // Spring converts to: SELECT * FROM products WHERE LOWER(name) LIKE LOWER('%searchTerm%')
//     List<Product> findByNameContainingIgnoreCase(String name); // Search term
    
//     // Method 5: Paginated search by category (USES INDEX + PAGINATION!)
//     // Like limit() and skip() in MongoDB - returns page of results
//     // Page<Product> is like { products: [...], totalPages: 5, currentPage: 1 }
//     Page<Product> findByCategory(String category,   // Category to filter
//                                  Pageable pageable); // Page number, 
    

// // Method 6: Custom query to find products by category, ordered by price
//     // @Query lets you write your own SQL (like writing raw MongoDB queries)
//     @Query("SELECT p FROM Product p WHERE p.category = :category ORDER BY p.price ASC")
// // Method 7
//      List<Product> findByCategoryOrderByPrice(@Param("category") String category);

//        // Method 8: DEMONSTRATION query WITHOUT using indexes
//     // This query will be SLOW because it searches in description field (no index)
//     // Used to show the difference between indexed vs non-indexed queries
//     @Query(value = "SELECT * FROM products WHERE UPPER(description) LIKE UPPER(CONCAT('%', :keyword, '%'))", 
//            nativeQuery = true) // nativeQuery = true means raw SQL (not JPQL)

//      List<Product> searchByDescriptionWithoutIndex(@Param("keyword") String keyword);


// }

// public class ProductRepository {
    
// }
