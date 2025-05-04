package com.anirudhgv.stockease.ui.owner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Inventory;
import com.anirudhgv.stockease.data.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    private List<Inventory> inventoryList = new ArrayList<>();
    public void setInventoryList(List<Inventory> list) {
        this.inventoryList = list;
        notifyDataSetChanged();
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
    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

    public static class InventoryViewHolder extends RecyclerView.ViewHolder {
        TextView textName,textSku, textQuantity;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textSku = itemView.findViewById(R.id.textSku);
            textQuantity = itemView.findViewById(R.id.textQuantity);
        }
    }

    public interface onItemClickListener {
        void onItemCLick(Inventory item);
    }
}
