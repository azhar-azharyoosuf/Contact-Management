package com.example.contactmanagement;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getClient(String baseUrl){
        if(retrofit==null){
            OkHttpClient.Builder httpClient=new OkHttpClient.Builder();// Create an OkHttpClient with the logging interceptor

            // Add the logging interceptor to log network requests and responses
            HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            // Build the OkHttpClient
            OkHttpClient client=httpClient.build();

            // Create the Retrofit instance with the OkHttpClient
            retrofit=new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
