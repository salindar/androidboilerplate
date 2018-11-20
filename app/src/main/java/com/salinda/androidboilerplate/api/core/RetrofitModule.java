package com.salinda.androidboilerplate.api.core;

import android.os.Bundle;

import com.google.gson.Gson;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Salinda
 */
//TODO add class level javadoc
public class RetrofitModule<T> {

    //TODO Inject APIO base url using injection
    private String apiUrl="https://jsonplaceholder.typicode.com";

    public RetrofitModule(){

    }
    private RequestInterceptor requestInterceptor;

    public T getRestClient(Class<T> t, Gson gson, Bundle bundle) {
        if(apiUrl==null){
            throw new NullPointerException("apiUrl is not defined, use dagger to properly inject apiUrl to RetrofitModule class");
        }
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(apiUrl);
       // if (gson != null) {
            builder.addConverterFactory(GsonConverterFactory.create());
       // }

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
