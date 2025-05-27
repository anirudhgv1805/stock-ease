package com.anirudhgv.stockease.ui.owner;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.User;

import java.util.List;

public class ManageUsersActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        RecyclerView recyclerView = findViewById(R.id.usersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(this, new UserAdapter.UserActionListener() {
            @Override
            public void onBlock(User user) {
                userViewModel.blockUser(user.getId(),
                        () -> Toast.makeText(getApplicationContext(), "User blocked", Toast.LENGTH_SHORT).show(),
                        () -> Toast.makeText(getApplicationContext(), "Failed to block user", Toast.LENGTH_SHORT).show()
                );
            }

            @Override
            public void onUnblock(User user) {
                userViewModel.unblockUser(user.getId(),
                        () -> Toast.makeText(getApplicationContext(), "User unblocked", Toast.LENGTH_SHORT).show(),
                        () -> Toast.makeText(getApplicationContext(), "Failed to unblock user", Toast.LENGTH_SHORT).show()
                );
            }
        });
        recyclerView.setAdapter(adapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, users -> {
            if (users != null) {
                adapter.setUsers(users);
            } else {
                Toast.makeText(this, "Failed to load users", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
