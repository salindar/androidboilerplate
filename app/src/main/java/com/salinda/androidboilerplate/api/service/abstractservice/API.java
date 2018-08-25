package com.salinda.androidboilerplate.api.service.abstractservice;

import com.salinda.androidboilerplate.api.core.APIResponseCallBack;

import retrofit2.http.GET;

/**
 * Created by Salinda
 */
public interface API {
    @GET("/api/authenticate")
    public void getUser(APIResponseCallBack<String> response);
}
