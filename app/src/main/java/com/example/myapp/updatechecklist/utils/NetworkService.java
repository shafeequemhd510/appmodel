package com.example.myapp.updatechecklist.utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class NetworkService {

    private static Retrofit retrofit_login = null;
    private static Retrofit retrofit_login_existing = null;
    private static Retrofit retrofit_LMS = null;
    private static Retrofit retrofit_LOS = null;
    private static Retrofit retrofit_LOS_media = null;
    private static Retrofit retrofit_LMS_media = null;
    private static Retrofit retrofit_VOT = null;
    private static Retrofit retrofit_BANK = null;
    private static Retrofit retrofit_BANK_IFSC = null;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public static String Token="";

    public static Retrofit getClient_LOS(final String key) {


        if (retrofit_LOS == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            retrofit_LOS = new Retrofit.Builder()
                    .baseUrl(UrlConstants.BASE_URL_LOS)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient.Builder()
                            .readTimeout(10, TimeUnit.SECONDS)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .addInterceptor(logging)
                            .addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {

                                    return  chain.proceed(chain.request().newBuilder().addHeader("Authorization", key).build());
                                }
                            })
                            .build())
                    .build();
        }
        return retrofit_LOS;
    }














}

