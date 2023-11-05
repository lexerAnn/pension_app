package com.android.pension_app.data.remote.interceptor;

import com.android.pension_app.data.local.SharedPref;
import com.android.pension_app.util.Constants;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class AuthenticationInterceptor implements Interceptor {
    private SharedPref sharedPrefFunctions;

    @Inject
    public AuthenticationInterceptor(SharedPref sharedPrefFunctions) {
        this.sharedPrefFunctions = sharedPrefFunctions;
    }

    @Override
    public Response intercept(Chain chain) {
        String authKey = sharedPrefFunctions.getPref(Constants.ACCESSTOKEN, null);
        Request request = chain.request();

        if (authKey == null || authKey.isEmpty()) {
            request = request.newBuilder()
                    .addHeader("Content-Type", CONTENT_TYPE_JSON)
                    .addHeader("Accept", "*/*")
                    .build();
        } else {
            Timber.tag("authKey").d("Bearer " + authKey);
            request = request.newBuilder()
                    .addHeader("Content-Type", CONTENT_TYPE_JSON)
                    .addHeader("Accept", "*/*")
                    .addHeader("Authorization", "Bearer " + authKey)
                    .build();
        }

        Timber.tag("request").d(request.headers("Authorization") + "\n" + request.url());
        try {
            return chain.proceed(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String CONTENT_TYPE_JSON = "application/json";
}
