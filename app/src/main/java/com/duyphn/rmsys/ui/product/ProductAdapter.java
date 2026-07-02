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
import com.duyphn.rmsys.model.Product;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.ProductViewHolder> {

    // Khởi tạo bộ so sánh DiffUtil để Android tự tính toán dòng nào thay đổi, không vẽ lại cả danh sách
    protected ProductAdapter() {
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
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_product,
                parent,
                false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = getItem(position);

        holder.tvProductName.setText(product.getName());
        holder.tvCategory.setText(R.string.default_category);

        Context context = holder.itemView.getContext();
        holder.tvPrice.setText(context.getString(R.string.product_price_format, product.getSellingPrice()));

        if (product.getStock() > 0) {
            holder.tvStatus.setText(R.string.status_available);
            holder.tvStatus.setTextColor(Color.parseColor("#2E7D32"));
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_available);
        } else {
            holder.tvStatus.setText(R.string.status_empty);
            holder.tvStatus.setTextColor(Color.parseColor("#C62828"));
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_empty);
        }

        int imageResId = product.getImageResId();
        if (imageResId != 0) {
            holder.imgProduct.setImageResource(imageResId);
        } else {
            holder.imgProduct.setImageResource(R.drawable.sample_product); // Ảnh mặc định nếu lỗi
        }
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvProductName, tvCategory, tvPrice, tvStatus;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
