package com.android.pension_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.pension_app.data.remote.User;
import com.android.pension_app.data.remote.response.CreateUserResponse;
import com.android.pension_app.data.remote.response.UsersResponse;
import com.android.pension_app.util.DataState;
import com.android.pension_app.util.DataStateListener;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    private final MutableLiveData<DataState<List<UsersResponse>>> usersLiveData = new MutableLiveData<>();


    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user, final DataStateListener<CreateUserResponse> dataStateListener) {
        userRepository.createUser(user, new DataStateListener<CreateUserResponse>() {
            @Override
            public void onLoading() {
                dataStateListener.onLoading(); // Notify that the operation is in progress
            }

            @Override
            public void onSuccess(CreateUserResponse data) {
                dataStateListener.onSuccess(data); // Handle success
            }

            @Override
            public void onError(String errorMessage) {
                dataStateListener.onError(errorMessage); // Handle error
            }
        });
    }



    public void getUser(DataStateListener<List<UsersResponse>> dataStateListener) {
        userRepository.getUsers(new DataStateListener<List<UsersResponse>>() {
            @Override
            public void onLoading() {
                dataStateListener.onLoading(); // Notify that the operation is in progress
            }

            @Override
            public void onSuccess(List<UsersResponse> data) {
                dataStateListener.onSuccess(data); // Handle success
            }

            @Override
            public void onError(String errorMessage) {
                dataStateListener.onError(errorMessage); // Handle error
            }
        });
    }


}






