package com.duyphn.rmsys.model;
import java.io.Serializable;
import java.util.UUID;

public class Category implements Serializable {
    private String id;
    private String name;
    private String description;
    private int imageResId;

    public Category() {
    }

    public Category(String name, String description, int imageResId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getImageResId() { return imageResId; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }
}
