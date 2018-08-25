package com.salinda.androidboilerplate.api.core;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Salinda
 */
//TODO add class level javadoc
public class RetrofitModule<T> {
    //TODO use dagger 2 injection
    @Inject
    @Named("api_base_url")
    private String apiUrl;
    //TODO use dagger 2 injection
    @Inject
    private RequestInterceptor requestInterceptor;

    public T getRestAdapter(Class<T> t, Gson gson, Bundle bundle) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(apiUrl);
        if (gson != null) {
            builder.addConverterFactory(GsonConverterFactory.create(gson));
        }

        /*
        Bundle available means there are some headers to be added to the request.
        This is only possible with adding interceptor to the OkHttpClient and add that
        OkHttpClient object to the Retrofit.Builder
         */
        if (bundle != null) {
            requestInterceptor.setBundle(bundle);
            //OK HTTP
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(requestInterceptor).build();
            builder.client(okHttpClient);
        }
        Retrofit retrofit = builder.build();
        return retrofit.create(t);
    }

}
