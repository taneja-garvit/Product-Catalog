package com.example.demo.entity;

import jakarta.persistence.*;  
import java.math.BigDecimal;   
import java.time.LocalDateTime; 




@Entity 

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
    private LocalDateTime createdDate;
    
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    public Product(){
        this.createdDate = LocalDateTime.now();
        this.updatedDate= LocalDateTime.now();
    }

    public Product(String name,
        String description,
        String category,
        BigDecimal price,
        Integer stockQuantity,
        String brand ){
            this(); 
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
        this.updatedDate = LocalDateTime.now();

    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
        this.updatedDate=LocalDateTime.now();
    }
     public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category; 
        this.updatedDate = LocalDateTime.now(); 
    }
    
    public BigDecimal getPrice() {
        return price; 
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price; 
        this.updatedDate = LocalDateTime.now();
    }
    
    public Integer getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity; 
        this.updatedDate = LocalDateTime.now(); 
    }
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
        this.updatedDate = LocalDateTime.now(); 
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate; 
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate; 
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate; 
    }
    
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate; 
    }

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

