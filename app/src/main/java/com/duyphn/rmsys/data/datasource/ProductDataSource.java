package com.duyphn.rmsys.data.datasource;

import android.content.Context;

import com.duyphn.rmsys.R;
import com.duyphn.rmsys.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDataSource {

    private final ProductDAO productDAO;
    public ProductDataSource(DatabaseHelper dbHelper) {
        this.productDAO = new ProductDAO(dbHelper);
    }

    public List<Product> getMockProducts() throws Exception {
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

    public Product getProductById(String id) throws Exception {
        try {
            return productDAO.findById(id);
        } catch (Exception e) {
            throw new Exception("Lỗi khi lấy sản phẩm từ database: " + e.getMessage(), e);
        }
    }

    public List<Product> getProductByName(String name) throws Exception {
        try {
            return productDAO.findByName(name);
        } catch (Exception e) {
            throw new Exception("Lỗi khi lấy danh sách sản phẩm từ database: " + e.getMessage(), e);
        }
    }

    public List<Product> getProductByCategoryId(String cat_id) throws Exception {
        try {
            return productDAO.findByCategoryId(cat_id);
        } catch (Exception e) {
            throw new Exception("Lỗi khi lấy danh sách sản phẩm từ database: " + e.getMessage(), e);
        }
    }

    public List<Product> getProducts() throws Exception {
        try {
            return productDAO.findByActiveState(true);
        } catch (Exception e) {
            throw new Exception("Lỗi khi lấy danh sách sản phẩm từ database: " + e.getMessage(), e);
        }
    }

    public boolean addProduct(Product product) throws Exception {
        try {
            if (product == null) {
                throw new IllegalArgumentException("Dữ liệu sản phẩm không được rỗng");
            }
            return productDAO.insert(product);
        } catch (Exception e) {
            throw new Exception("Lỗi khi thêm sản phẩm vào Database: " + e.getMessage(), e);
        }
    }
}
