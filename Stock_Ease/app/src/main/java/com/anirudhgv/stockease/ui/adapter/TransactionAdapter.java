package com.anirudhgv.stockease.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Order;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<Order> transactions = new ArrayList<>();

    public void setOrders(List<Order> orders) {
        this.transactions = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Order order = transactions.get(position);
        holder.txtItem.setText("Item: " + order.getItem());
        holder.txtQuantity.setText("Qty: " + order.getQuantity());
        holder.txtClient.setText("Client: " + order.getClientName());
        holder.txtPrice.setText("Price: ₹" + order.getPrice());
        holder.txtDateTime.setText("Time: " + order.getDateTime());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView txtItem, txtQuantity, txtClient, txtPrice, txtDateTime;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtClient = itemView.findViewById(R.id.txtClientName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtDateTime = itemView.findViewById(R.id.txtDateTime);
        }
    }
}
