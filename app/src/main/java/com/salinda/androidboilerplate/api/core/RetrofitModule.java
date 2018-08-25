package com.salinda.androidboilerplate.api.core;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Salinda
 */
public class RetrofitModule<T> {

    @Inject
    @Named("api_base_url")
    private String apiUrl;
    @Inject
    private RequestInterceptor requestInterceptor;

    public T getRestAdapter(Class<T> t, Gson gson, Bundle bundle) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(apiUrl);
        if (gson != null) {
            GsonConverter converter = new GsonConverter(gson);
            builder.setConverter(converter);
        }
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        if (bundle != null) {
            requestInterceptor.setBundle(bundle);
            builder.setRequestInterceptor(requestInterceptor);
        }
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(t);
    }

}
