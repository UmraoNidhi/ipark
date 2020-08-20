package com.iparksimple.app.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private String mApiKey;
    private static String url = "http://park.ignitershub.com/api/";
    private static String ImageBaseUrl ="http://18.189.202.50/imRD/";

//    public ApiClient(String apiKey) {
//        mApiKey = KeyGenerationClass.getEncryptedKey();
//    }
//                    .connectTimeout(60,TimeUnit.SECONDS)


    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
