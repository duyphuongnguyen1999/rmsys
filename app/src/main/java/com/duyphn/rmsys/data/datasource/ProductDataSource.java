package com.duyphn.rmsys.data.datasource;

import com.duyphn.rmsys.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDataSource {

    public List<Product> getProducts() throws Exception {
        // TODO: Viết code đọc SQLite hoặc gọi API thực tế ở đây
        List<Product> mockList = new ArrayList<>();
        mockList.add(new Product("Sữa tươi", "cat1", 15000, 10000, 50, "Hộp"));
        return mockList;
    }

    public boolean addProduct(Product product) throws Exception {
        // TODO: Viết code INSERT vào SQLite hoặc POST lên API thực tế
        return true;
    }
}
