package com.duyphn.rmsys.data.datasource;

import android.content.Context;

import com.duyphn.rmsys.R;
import com.duyphn.rmsys.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDataSource {
    private final DatabaseHelper dbHelper;

    public ProductDataSource(Context context) {
        this.dbHelper = DatabaseHelper.getInstance(context);
    }

    public List<Product> getProducts() throws Exception {
        // TODO: Viết code đọc SQLite hoặc gọi API thực tế ở đây
        List<Product> mockList = new ArrayList<>();
        mockList.add(new Product(
                "Sữa tươi",
                "cat1",
                15000,
                10000,
                50,
                "Hộp",
                R.drawable.sample_product,
                "Description")
        );

        mockList.add(new Product(
                "Coca Cola 330ml",
                "cat1",
                15000,
                10000,
                50,
                "Lon",
                R.drawable.sample_product,
                "Description")
        );
        return mockList;
    }

    public boolean addProduct(Product product) throws Exception {
        // TODO: Viết code INSERT vào SQLite hoặc POST lên API thực tế
        return true;
    }
}
