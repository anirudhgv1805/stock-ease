package com.anirudhgv.stockease.ui.owner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Inventory;
import com.anirudhgv.stockease.data.model.Product;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    private List<Inventory> inventoryList = new ArrayList<>();
    private onItemClickListener listener;
    public void setInventoryList(List<Inventory> list) {
        this.inventoryList = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory, parent, false);
        return new InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        Inventory inventory = inventoryList.get(position);
        Product product = inventory.getProduct();

        holder.textName.setText("Name: " + product.getName());
        holder.textSku.setText("SKU: " + product.getSku());
        holder.textQuantity.setText("Quantity: " + inventory.getQuantity());

        String imageUrl = product.getProductImgUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.productImg);
        }

        holder.itemView.setOnClickListener(v -> {
            if(listener != null) {
                listener.onItemCLick(inventory);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

    public static class InventoryViewHolder extends RecyclerView.ViewHolder {
        TextView textName,textSku, textQuantity;
        ImageView productImg;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.imageItem);
            textName = itemView.findViewById(R.id.textName);
            textSku = itemView.findViewById(R.id.textSku);
            textQuantity = itemView.findViewById(R.id.textQuantity);
        }
    }

    public interface onItemClickListener {
        void onItemCLick(Inventory item);
    }
}
