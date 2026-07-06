package com.duyphn.rmsys.ui.main;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.duyphn.rmsys.R;
import com.duyphn.rmsys.databinding.ActivityMainBinding; // Import Binding tự sinh

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Khởi tạo View Binding cho Activity tổng
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Áp dụng khoảng trống hệ thống lên lớp nền ngoài cùng R.id.main để tránh lỗi lệch Toolbar ở Fragment
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0); // Không padding đáy vì BottomNav lo rồi
            return insets;
        });

        // Tìm kiếm cấu trúc điều hướng thông qua biến binding gọn gàng
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            // Thiết lập liên kết tự động đổi tab
            NavigationUI.setupWithNavController(binding.bottomNav, navController);
        }
    }
}
