package com.anirudhgv.stockease.ui.owner;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.anirudhgv.stockease.data.model.User;
import com.anirudhgv.stockease.data.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private final UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application.getApplicationContext());
    }

    public LiveData<List<User>> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void blockUser(Long id, Runnable onSuccess, Runnable onFailure) {
        userRepository.blockUser(id, onSuccess, onFailure);
    }

    public void unblockUser(Long id, Runnable onSuccess, Runnable onFailure) {
        userRepository.unblockUser(id, onSuccess, onFailure);
    }
}
