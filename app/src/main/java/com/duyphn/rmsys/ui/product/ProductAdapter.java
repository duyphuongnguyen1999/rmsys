package com.duyphn.rmsys.ui.product;

import androidx.annotation.NonNull;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.duyphn.rmsys.R;
import com.duyphn.rmsys.databinding.ItemProductBinding;
import com.duyphn.rmsys.model.Product;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.ProductViewHolder> {

    // 1. Định nghĩa Interface để Fragment đăng ký lắng nghe
    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    private final OnItemClickListener listener;



    // Khởi tạo bộ so sánh DiffUtil để Android tự tính toán dòng nào thay đổi, không vẽ lại cả danh sách
    protected ProductAdapter(OnItemClickListener listener) {
        super(new DiffUtil.ItemCallback<>() {
            @Override
            public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem.getName().equals(newItem.getName()) &&
                        oldItem.getSellingPrice() == newItem.getSellingPrice() &&
                        oldItem.getStock() == newItem.getStock();
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = getItem(position);

        holder.binding.tvProductName.setText(product.getName());
        holder.binding.tvCategory.setText(R.string.default_category);

        Context context = holder.itemView.getContext();
        holder.binding.tvPrice.setText(context.getString(R.string.product_price_format, product.getSellingPrice()));

        if (product.getStock() > 0) {
            holder.binding.tvStatus.setText(R.string.status_available);
            holder.binding.tvStatus.setTextColor(Color.parseColor("#2E7D32"));
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_status_available);
        } else {
            holder.binding.tvStatus.setText(R.string.status_empty);
            holder.binding.tvStatus.setTextColor(Color.parseColor("#C62828"));
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_status_empty);
        }

        int imageResId = product.getImageResId();
        if (imageResId != 0) {
            holder.binding.imgProductDetail.setImageResource(imageResId);
        } else {
            holder.binding.imgProductDetail.setImageResource(R.drawable.sample_product); // Ảnh mặc định nếu lỗi
        }
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        final ItemProductBinding binding;

        public ProductViewHolder(@NonNull ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
