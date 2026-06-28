package com.duyphn.rmsys.model;

import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable {

    private String id;
    private String name;
    private String categoryId;
    private double salePrice;
    private double importPrice;
    private int stock;
    private String unit;

    public Product() {
    }

    public Product(String id, String name, String categoryId, double salePrice, double importPrice, int stock, String unit) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.salePrice = salePrice;
        this.importPrice = importPrice;
        this.stock = stock;
        this.unit = unit;
    }

    // Auto generate id constructor
    public Product(String name, String categoryId, double salePrice, double importPrice, int stock, String unit) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.categoryId = categoryId;
        this.salePrice = salePrice;
        this.importPrice = importPrice;
        this.stock = stock;
        this.unit = unit;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public double getSalePrice() { return salePrice; }
    public void setSalePrice(double salePrice) { this.salePrice = salePrice; }

    public double getImportPrice() { return importPrice; }
    public void setImportPrice(double importPrice) { this.importPrice = importPrice; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
