package com.android.pension_app.data.remote;

import com.android.pension_app.data.remote.response.CreateUserResponse;
import com.android.pension_app.data.remote.response.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PensionApi {

    @POST("users")
    Call<CreateUserResponse> createUser(User user);

    @GET("todos")
    Call<List<UsersResponse>> getUsers();

}
