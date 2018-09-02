package com.example.development.sakaiclient20.networking.services;

import android.content.Context;

import com.example.development.sakaiclient20.R;
import com.example.development.sakaiclient20.networking.utilities.HeaderInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Development on 8/5/18.
 */

public class ServiceFactory {

    public static <Service> Service getService(Context context, Class<Service> serviceClass) {
        // Get the base url for the Sakai API
        String baseUrl = context.getString(R.string.BASE_URL);
        // Get the url which has the relevant cookies for Sakai
        String cookieUrl = context.getString(R.string.COOKIE_URL_1);

        // Create the custom OkHttpClient with the interceptor to inject
        // cookies into every request
        HeaderInterceptor interceptor = new HeaderInterceptor(cookieUrl);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        // The Retrofit instance allows us to construct our own services
        // that will make network requests
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(serviceClass);
    }
}
