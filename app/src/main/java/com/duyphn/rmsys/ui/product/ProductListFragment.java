package com.duyphn.rmsys.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.duyphn.rmsys.data.datasource.ProductDataSource;
import com.duyphn.rmsys.data.repository.ProductRepository;
import com.duyphn.rmsys.databinding.FragmentProductListBinding;
import com.duyphn.rmsys.model.Product;
import com.duyphn.rmsys.model.Result;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class ProductListFragment extends Fragment {

    private FragmentProductListBinding binding;
    private ProductViewModel productViewModel;
    private ProductAdapter adapter;

    public ProductListFragment() {
    }

    public static ProductListFragment newInstance() {
        return new ProductListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Khởi tạo đối tượng View Binding kết nối với file fragment_product_list.xml
        binding = FragmentProductListBinding.inflate(inflater, container, false);

        // Bắt sự kiện click floating button add product
        binding.fabAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), AddProductActivity.class);
            startActivity(intent);
        });

        // Bắt sự kiện chuyển đổi tab để lọc danh sách
        binding.tabLayoutFilter.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Lấy lại danh sách gốc sạch từ khay trạng thái LiveData của ViewModel
                Result<List<Product>> currentResult = productViewModel.getListResult().getValue();
                if (!(currentResult instanceof Result.Success)) {
                    return;
                }

                List<Product> allProducts = ((Result.Success<List<Product>>) currentResult).getData();
                java.util.List<Product> filteredList = new java.util.ArrayList<>();

                int position = tab.getPosition();
                if (position == 0) {
                    // Tab "Tất cả"
                    adapter.submitList(allProducts);
                } else if (position == 1) {
                    // Tab "Còn hàng" (Lọc những sản phẩm có stock > 0)
                    for (Product p : allProducts) {
                        if (p.getStock() > 0) {
                            filteredList.add(p);
                        }
                    }
                    adapter.submitList(filteredList);
                } else if (position == 2) {
                    // Tab "Hết hàng" (Lọc những sản phẩm có stock == 0)
                    for (Product p : allProducts) {
                        if (p.getStock() == 0) {
                            filteredList.add(p);
                        }
                    }
                    adapter.submitList(filteredList);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Cấu hình giao diện hiển thị cho RecyclerView
        adapter = new ProductAdapter(product -> {
            Intent intent = new Intent(requireContext(), ProductDetailActivity.class);
            intent.putExtra("EXTRA_PRODUCT", product);
            startActivity(intent);
        });
        binding.recyclerProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerProducts.setAdapter(adapter);

        // 2. Khởi tạo cây phụ thuộc dữ liệu (Dependency Injection thủ công theo Clean Architecture)
        ProductDataSource dataSource = new ProductDataSource(requireContext());
        ProductRepository repository = ProductRepository.getInstance(dataSource);
        ProductViewModelFactory factory = new ProductViewModelFactory(repository);

        // Liên kết và lấy ra thực thể ProductViewModel
        productViewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);

        // 3. Đăng ký lắng nghe (Observe) khay trạng thái dữ liệu từ ViewModel đẩy ra
        productViewModel.getListResult().observe(getViewLifecycleOwner(), result -> {
            if (result == null) return;

            if (result instanceof Result.Success) {
                // Trường hợp Thành công: Bóc tách danh sách List<Product> sạch và đổ thẳng vào Adapter
                List<Product> data = ((Result.Success<List<Product>>) result).getData();
                adapter.submitList(data);
            } else if (result instanceof Result.Error) {
                // Trường hợp Thất bại: Lấy thông báo lỗi ra hiển thị Toast báo lỗi cho người dùng
                String errorMsg = ((Result.Error) result).getError().getMessage();
                Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show();
            }
        });

        // 4. Phát lệnh kích hoạt hàm tải danh sách sản phẩm chạy ngầm (Bất đồng bộ)
        productViewModel.loadProducts();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Bắt buộc phải giải phóng Binding khi Fragment bị hủy giao diện để tránh rò rỉ bộ nhớ (Memory Leak)
        binding = null;
    }
}
