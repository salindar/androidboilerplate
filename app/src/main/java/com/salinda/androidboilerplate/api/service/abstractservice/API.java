package com.salinda.androidboilerplate.api.service.abstractservice;

import com.salinda.androidboilerplate.api.core.APIResponseCallBack;
import com.salinda.androidboilerplate.model.SampleModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Salinda
 */
public interface API {
    @GET("posts/1")
    Call<SampleModel> getUser();
}
