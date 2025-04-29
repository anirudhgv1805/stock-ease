package com.anirudhgv.stockease.ui.owner;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anirudhgv.stockease.data.model.dto.OwnerDashboardData;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.repository.OwnerDashboardRepository;
import com.anirudhgv.stockease.data.storage.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerViewModel extends AndroidViewModel {

    private final OwnerDashboardRepository ownerDashboardRepository;
    private LiveData<OwnerDashboardData> ownerDashboardDataLiveData;

    public OwnerViewModel(@NonNull Application application) {
        super(application);
        this.ownerDashboardRepository = new OwnerDashboardRepository(application.getApplicationContext());
    }

    public void loadOwnerDashboardData() {
        ownerDashboardDataLiveData = ownerDashboardRepository.getOwnerDashboardData();
    }

    public LiveData<OwnerDashboardData> getOwnerDashboardData() {
        return ownerDashboardDataLiveData;
    }
}
