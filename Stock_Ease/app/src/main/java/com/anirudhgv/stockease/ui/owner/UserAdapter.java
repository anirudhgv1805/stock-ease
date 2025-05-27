package com.anirudhgv.stockease.ui.owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    public interface UserActionListener {
        void onBlock(User user);

        void onUnblock(User user);
    }

    private List<User> users = new ArrayList<>();
    private final Context context;
    private final UserActionListener listener;

    public UserAdapter(Context context, UserActionListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.usernameText.setText(user.getUsername());
        holder.emailText.setText(user.getEmail());
        holder.roleText.setText(user.getRole());

        if (Boolean.TRUE.equals(user.getIsBlocked())) {
            holder.actionButton.setText("Unblock");
            holder.actionButton.setOnClickListener(v -> listener.onUnblock(user));
        } else {
            holder.actionButton.setText("Block");
            holder.actionButton.setOnClickListener(v -> listener.onBlock(user));
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView usernameText, emailText, roleText;
        Button actionButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.userName);
            emailText = itemView.findViewById(R.id.userEmail);
            roleText = itemView.findViewById(R.id.userRole);
            actionButton = itemView.findViewById(R.id.blockUnblockBtn);
        }
    }
}

