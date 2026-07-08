package com.duyphn.rmsys.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.duyphn.rmsys.R;
import com.duyphn.rmsys.databinding.FragmentHomeBinding;
import com.duyphn.rmsys.ui.product.ProductListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private boolean isNotificationEnabled = true;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        // Load spinner items
        Spinner spinner = binding.spTimeFilter;

        String[] timeOptions = { "Hôm nay", "7 ngày qua", "Tháng này", "Năm nay" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
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
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.cvStatProduct.setOnClickListener(v -> {
//            NavController navController = NavHostFragment.findNavController(this);
//            navController.navigate(R.id.productFragment);
            // Find BottomNavigationView
            BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottomNav);
            // Navigate to productFragment
            bottomNavigationView.setSelectedItemId(R.id.productFragment);
        });

        binding.cvStatCustomer.setOnClickListener(v -> {
            // TODO: Implement Customer's resources
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

        binding.toolbarHome.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.action_notifications) {
                // Toggle Notification state
                isNotificationEnabled = !isNotificationEnabled;

                if (isNotificationEnabled) {
                    item.setIcon(R.drawable.ic_notifications);
                    Toast.makeText(requireContext(), "Đã bật thông báo", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    item.setIcon(R.drawable.ic_notifications_off);
                    Toast.makeText(requireContext(), "Đã tắt thông báo", Toast.LENGTH_SHORT).show();
                    return true;
                }
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
                // TODO: Implement Profile Activity and resources
            } else if (itemId == R.id.nav_history) {
                Toast.makeText(requireContext(), "Mở trang Lịch sử", Toast.LENGTH_SHORT).show();
                // TODO: Implement History Activity and resources
            } else if (itemId == R.id.nav_logout) {
                Toast.makeText(requireContext(), "Thực hiện Đăng xuất", Toast.LENGTH_SHORT).show();
                // TODO: Implement Logout Activity and resources
            }

            binding.drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        });



        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}