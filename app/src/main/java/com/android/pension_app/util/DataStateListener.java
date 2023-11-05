package com.android.pension_app.util;

public interface DataStateListener<T> {
    void onLoading();
    void onSuccess(T data);
    void onError(String errorMessage);
}
