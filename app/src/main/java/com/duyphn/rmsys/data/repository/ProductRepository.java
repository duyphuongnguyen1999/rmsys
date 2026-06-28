package com.duyphn.rmsys.data.repository;

import com.duyphn.rmsys.data.datasource.ProductDataSource;
import com.duyphn.rmsys.model.Product;
import com.duyphn.rmsys.model.Result;

import java.io.IOException;
import java.util.List;

public class ProductRepository {
    private static volatile ProductRepository instance;
    private final ProductDataSource dataSource;

    private ProductRepository(ProductDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProductRepository getInstance(ProductDataSource dataSource) {
        if (instance == null) {
            synchronized (ProductRepository.class) {
                if (instance == null) {
                    instance = new ProductRepository(dataSource);
                }
            }
        }
        return instance;
    }

    public Result<List<Product>> getProducts() {
        try {
            return new Result.Success<>(dataSource.getProducts());
        } catch (Exception e) {
            return new Result.Error(new IOException("Không thể tải danh sách sản phẩm", e));
        }
    }

    public Result<Boolean> addProduct(Product product) {
        try {
            boolean success = dataSource.addProduct(product);
            return new Result.Success<>(success);
        } catch (Exception e) {
            return new Result.Error(new IOException("Không thể thêm sản phẩm mới", e));
        }
    }
}
