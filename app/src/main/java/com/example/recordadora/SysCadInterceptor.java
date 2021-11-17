package com.example.recordadora;

import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class SysCadInterceptor implements Interceptor {

    private String credentials;

    public SysCadInterceptor(String basicC){
        this.credentials=basicC;
    }
    public SysCadInterceptor(String user, String pass) {
        this.credentials = Credentials.basic(user, pass);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder().header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }

}