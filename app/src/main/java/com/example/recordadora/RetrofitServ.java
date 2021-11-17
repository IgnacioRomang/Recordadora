package com.example.recordadora;

import android.text.TextUtils;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServ {

    public static final String URL = "https://recordatorio-api.duckdns.org/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> clase, String user, String pass) {
        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
            String token = Credentials.basic(user, pass);
            if (!TextUtils.isEmpty(token)) {
                SysCadInterceptor interceptor = new SysCadInterceptor(token);
                if (!httpClient.interceptors().contains(interceptor)) {
                    httpClient.addInterceptor(interceptor);
                    builder.client(httpClient.build());
                    retrofit = builder.build();
                }
            }
            return retrofit.create(clase);
        }
        return retrofit.create(clase);
    }
}