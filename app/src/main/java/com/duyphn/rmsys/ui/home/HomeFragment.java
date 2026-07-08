package com.duyphn.rmsys.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.duyphn.rmsys.R;
import com.duyphn.rmsys.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private boolean isNotificationEnabled = true;

    private BottomNavigationView bottomNavigationView;

    public HomeFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomNavigationView = requireActivity().findViewById(R.id.bottomNav);
        setupTimeFilterSpinner();
        setupCardStatisticsClickListeners();
        setupToolbarAndDrawerActions();

    }

    private void setupTimeFilterSpinner() {
        Spinner spinner = binding.spTimeFilter;
        String[] timeOptions = { "Hôm nay", "7 ngày qua", "Tháng này", "Năm nay" };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.item_spinner_text,
                timeOptions
        );
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown_text);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO: Implement revenue chart filtering logic
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupCardStatisticsClickListeners() {
        binding.cvStatProduct.setOnClickListener(v -> {
            if (bottomNavigationView != null) {
                bottomNavigationView.setSelectedItemId(R.id.productFragment);
            }
        });

        binding.cvStatCustomer.setOnClickListener(v -> {
            if (bottomNavigationView != null) {
                bottomNavigationView.setSelectedItemId(R.id.customerFragment);
            }
        });

        binding.cvStatCategory.setOnClickListener(v -> {
            // TODO: Implement Category's resources
        });

        binding.cvStatLocation.setOnClickListener(v -> {
            // TODO: Implement Location's resources
        });

        binding.cvUserAvatar.setOnClickListener(v -> {
            // TODO: Implement User's resources
        });
    }

    private void setupToolbarAndDrawerActions() {
        binding.toolbarHome.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_notifications) {
                isNotificationEnabled = !isNotificationEnabled;
                if (isNotificationEnabled) {
                    item.setIcon(R.drawable.ic_notifications);
                    Toast.makeText(requireContext(), "Đã bật thông báo", Toast.LENGTH_SHORT).show();
                } else {
                    item.setIcon(R.drawable.ic_notifications_off);
                    Toast.makeText(requireContext(), "Đã tắt thông báo", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        });

        binding.toolbarHome.setNavigationOnClickListener(v -> {
            binding.drawerLayout.openDrawer(GravityCompat.START);
        });

        binding.navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_profile) {
                Toast.makeText(requireContext(), "Mở trang Hồ sơ", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.nav_history) {
                Toast.makeText(requireContext(), "Mở trang Lịch sử", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.nav_logout) {
                Toast.makeText(requireContext(), "Thực hiện Đăng xuất", Toast.LENGTH_SHORT).show();
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
