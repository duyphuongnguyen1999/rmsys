package com.duyphn.rmsys.model;

import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable {

    private String id;
    private String name;
    private String categoryId;
    private double sellingPrice;
    private double importPrice;
    private int stock;
    private String unit;
    private int imageResId;
    private String description;

    public Product() {
    }

    public Product(String id, String name, String categoryId, double sellingPrice, double importPrice, int stock, String unit, int imageResId, String description) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.sellingPrice = sellingPrice;
        this.importPrice = importPrice;
        this.stock = stock;
        this.unit = unit;
        this.imageResId = imageResId;
        this.description = description;
    }

    // Auto generate id constructor
    public Product(String name, String categoryId, double sellingPrice, double importPrice, int stock, String unit, int imageResId, String description) {
        this.description = description;
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.categoryId = categoryId;
        this.sellingPrice = sellingPrice;
        this.importPrice = importPrice;
        this.stock = stock;
        this.unit = unit;
        this.imageResId = imageResId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public double getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(double sellingPrice) { this.sellingPrice = sellingPrice; }

    public double getImportPrice() { return importPrice; }
    public void setImportPrice(double importPrice) { this.importPrice = importPrice; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public int getImageResId() { return imageResId; }

    public void setImageResId(int imageResId) { this.imageResId = imageResId; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
