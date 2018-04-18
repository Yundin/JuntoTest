package com.yundin.gladstest.model.api;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Yundin Vladislav
 */
public class ApiFactory {

    private static volatile ApiInterface apiInterface;

    public static final String BASE_URL = "https://api.producthunt.com/";

    public static ApiInterface getApiInterface() {
        ApiInterface mInterface = apiInterface;
        if (mInterface == null) {
            synchronized (ApiFactory.class) {
                mInterface = apiInterface;
                if (mInterface == null) {
                    mInterface = apiInterface = buildRetrofit().create(ApiInterface.class);
                }
            }
        }
        return mInterface;
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
