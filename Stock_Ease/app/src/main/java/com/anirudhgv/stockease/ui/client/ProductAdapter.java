package com.anirudhgv.stockease.ui.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList;
    private final Context context;
    private final OnProductClickListener onProductClickListener;

    private final Map<Long, Integer> productQuantities = new HashMap<>();

    public ProductAdapter(Context context, List<Product> productList, OnProductClickListener onProductClickListener) {
        this.context = context;
        this.productList = productList;
        this.onProductClickListener = onProductClickListener;
    }

    public Map<Long, Integer> getProductQuantities() {
        return productQuantities;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.format("₹%.2f", product.getPrice()));

        if (!productQuantities.containsKey(product.getId())) {
            productQuantities.put(product.getId(), 0);
        }

        holder.quantityInput.setText(String.valueOf(productQuantities.get(product.getId())));

        holder.quantityInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                try {
                    int quantity = Integer.parseInt(holder.quantityInput.getText().toString());
                    productQuantities.put(product.getId(), quantity > 0 ? quantity : 1);
                } catch (NumberFormatException e) {
                    productQuantities.put(product.getId(), 1); // fallback
                }
            }
        });

        holder.itemView.setOnClickListener(v -> onProductClickListener.onProductClick(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        EditText quantityInput;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            quantityInput = itemView.findViewById(R.id.quantity_input);
        }
    }

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }
}
