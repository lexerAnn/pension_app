package com.android.pension_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.pension_app.data.remote.PensionApi;
import com.android.pension_app.data.remote.User;
import com.android.pension_app.data.remote.response.CreateUserResponse;
import com.android.pension_app.data.remote.response.UsersResponse;
import com.android.pension_app.util.DataState;
import com.android.pension_app.util.DataStateListener;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private PensionApi pensionApi;

    @Inject
    public UserRepository(PensionApi pensionApi) {
        this.pensionApi = pensionApi;
    }


    public void createUser(User user, DataStateListener<CreateUserResponse> dataStateListener) {
        dataStateListener.onLoading(); // Notify that the operation is in progress

        pensionApi.createUser(user).enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                if (response.isSuccessful()) {
                    dataStateListener.onSuccess(response.body());
                } else {
                    dataStateListener.onError("Failed to create a new user.");
                }
            }

            @Override
            public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                dataStateListener.onError("Network request failed.");
            }
        });
    }

    public void getUsers(DataStateListener<List<UsersResponse>> dataStateListener) {
        dataStateListener.onLoading(); // Notify that the operation is in progress

        pensionApi.getUsers().enqueue(new Callback<List<UsersResponse>>() {
            @Override
            public void onResponse(Call<List<UsersResponse>> call, Response<List<UsersResponse>> response) {
                if (response.isSuccessful()) {
                    dataStateListener.onSuccess(response.body());
                } else {
                    dataStateListener.onError("Failed to fetch users.");
                }
            }

            @Override
            public void onFailure(Call<List<UsersResponse>> call, Throwable t) {
                dataStateListener.onError("Network request failed.");
            }
        });
    }

}
