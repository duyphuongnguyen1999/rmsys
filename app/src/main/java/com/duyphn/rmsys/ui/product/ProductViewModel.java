package com.duyphn.rmsys.ui.product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duyphn.rmsys.data.repository.ProductRepository;
import com.duyphn.rmsys.model.Product;
import com.duyphn.rmsys.model.Result;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private final ProductRepository repository;

    private final MutableLiveData<Result<List<Product>>> listResult = new MutableLiveData<>();
    private final MutableLiveData<Result<Boolean>> addResult = new MutableLiveData<>();

    public ProductViewModel(ProductRepository repository) {
        this.repository = repository;
    }

    public LiveData<Result<List<Product>>> getListResult() { return listResult;}
    public LiveData<Result<Boolean>> getAddResult() { return addResult; }

    public void loadProducts() {
        listResult.setValue(repository.getProducts());
    }

    @SuppressWarnings("unchecked")
    public void addProduct(
            String name, String catId, String sellingPriceStr, String importPriceStr,
            String stockStr, String unit, int imageResId, String description) {
        // Logic Validate dữ liệu thô đầu vào
        if (name == null || name.trim().isEmpty()) {
            addResult.setValue(new Result.Error(new Exception("Tên sản phẩm không được để trống")));
            return;
        }
        if (sellingPriceStr == null || sellingPriceStr.trim().isEmpty()) {
            addResult.setValue(new Result.Error(new Exception("Vui lòng nhập giá bán")));
            return;
        }

        try {
            double sellPrice = Double.parseDouble(sellingPriceStr);
            double impPrice = importPriceStr.isEmpty() ? 0 : Double.parseDouble(importPriceStr);
            int stock = stockStr.isEmpty() ? 0 : Integer.parseInt(stockStr);

            Product newProduct = new Product(name, catId, sellPrice, impPrice, stock, unit, imageResId, description);
            Result<Boolean> result = repository.addProduct(newProduct);
            addResult.setValue(result);

        } catch (NumberFormatException e) {
            addResult.setValue(new Result.Error(new Exception("Giá tiền hoặc số lượng nhập vào sai định dạng")));
        }
    }


}
