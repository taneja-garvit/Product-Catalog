package com.example.demo.entity;

import jakarta.persistence.*;  // JPA annotations for database mapping
import java.math.BigDecimal;   // For precise decimal numbers (like money)
import java.time.LocalDateTime; // For date/time handling (like Date in JS)




@Entity // @Entity tells Spring: "This class represents a database table"

@Table(name="products",
indexes = {
    @Index(name="idx_category", columnList = "category"),
    @Index(name="idx_price", columnList = "price"),
    @Index(name="idx_category_price", columnList = "category,price"),
    @Index(name="idx_created_date", columnList = "createdDate")
    })

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false,
    precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(nullable = false)
    private String brand;

     @Column(nullable = false)
    private LocalDateTime createdDate; // When product was added
    
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    public Product(){
        this.createdDate = LocalDateTime.now();
        this.updatedDate= LocalDateTime.now();
    }

    // CUSTOM CONSTRUCTOR for easy object creation
    public Product(String name,
        String description,
        String category,
        BigDecimal price,
        Integer stockQuantity,
        String brand ){
            this();  // Call default constructor first (sets timestamps)
            this.name = name;
            this.description = description;
            this.category = category;
            this.price = price;
            this.stockQuantity = stockQuantity;
            this.brand = brand;
    }

    //Getter setter
    public long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
        this.updatedDate = LocalDateTime.now(); // Update timestamp when changed

    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
        this.updatedDate=LocalDateTime.now();
    }
     public String getCategory() {
        return category; // Returns product category
    }
    
    public void setCategory(String category) {
        this.category = category; // Sets product category
        this.updatedDate = LocalDateTime.now(); // Update timestamp
    }
    
    // PRICE field getters/setters (INDEXED - fast price queries)
    public BigDecimal getPrice() {
        return price; // Returns product price
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price; // Sets product price
        this.updatedDate = LocalDateTime.now(); // Update timestamp
    }
    
    // STOCK QUANTITY field getters/setters
    public Integer getStockQuantity() {
        return stockQuantity; // Returns stock amount
    }
    
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity; // Sets stock amount
        this.updatedDate = LocalDateTime.now(); // Update timestamp
    }
    
    // BRAND field getters/setters
    public String getBrand() {
        return brand; // Returns product brand
    }
    
    public void setBrand(String brand) {
        this.brand = brand; // Sets product brand
        this.updatedDate = LocalDateTime.now(); // Update timestamp
    }
    
    // TIMESTAMP getters/setters
    public LocalDateTime getCreatedDate() {
        return createdDate; // When product was created
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate; // Sets creation date
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate; // When product was last updated
    }
    
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate; // Sets update date
    }

      // OPTIONAL: toString method for easy debugging
    // Like JSON.stringify() in JavaScript - shows object as string
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", brand='" + brand + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }


}


// The products table in the provided Spring Boot project has 9 columns (as you correctly noted: id, name, description, category, price, stockQuantity, brand, createdDate, updatedDate). However, the indexes (idx_category, idx_price, idx_category_price, idx_created_date) are not columns in the table. They are separate database structures that optimize query performance for specific columns.

// Columns: These are the actual fields in the products table where data is stored (e.g., id, name, category, etc.).
// Indexes: These are metadata structures that help MySQL find data faster for queries involving the indexed columns (e.g., category, price, createdDate). They are not part of the table’s data and do not appear as columns in the table’s output.

// Clarification:

// The products table has 9 columns:

// id (BIGINT, Primary Key)
// name (VARCHAR)
// description (VARCHAR)
// category (VARCHAR)
// price (DECIMAL)
// stockQuantity (INT)
// brand (VARCHAR)
// createdDate (DATETIME)
// updatedDate (DATETIME)


// The table has 4 indexes:

// idx_category (on the category column)
// idx_price (on the price column)
// idx_category_price (a composite index on category and price)
// idx_created_date (on the createdDate column)



// These indexes are not columns and do not appear in the table’s data when you query it (e.g., SELECT * FROM products). Instead, they are used by MySQL internally to speed up queries like SELECT * FROM products WHERE category = 'Electronics'.