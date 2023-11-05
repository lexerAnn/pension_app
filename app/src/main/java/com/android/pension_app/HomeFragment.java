package com.android.pension_app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.pension_app.data.remote.User;
import com.android.pension_app.data.remote.response.CreateUserResponse;
import com.android.pension_app.data.remote.response.UsersResponse;
import com.android.pension_app.util.DataStateListener;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {


    private UserViewModel userViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);


        //Making post
        /*
        userViewModel.createUser(new User(1,"Leslie"), new DataStateListener<CreateUserResponse>() {
            @Override
            public void onLoading() {
                // Handle loading state (show progress bar, etc.)
            }

            @Override
            public void onSuccess(CreateUserResponse data) {
                // Handle success (user created)
            }

            @Override
            public void onError(String errorMessage) {
                // Handle error (creation failed)
            }
        });

         */


        userViewModel.getUser(new DataStateListener<List<UsersResponse>>() {
            @Override
            public void onLoading() {
                // Handle loading state (show progress bar, etc.)
            }

            @Override
            public void onSuccess(List<UsersResponse> data) {
                Log.d("Users->>>", data.get(0).getTitle());

            }

            @Override
            public void onError(String errorMessage) {
                // Handle error (creation failed)
            }
        });


        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}