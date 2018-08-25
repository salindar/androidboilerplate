package com.salinda.androidboilerplate.model;

import android.os.Parcel;

import retrofit2.Response;

/**
 * Created by Salinda.
 */
public class RetrofitResponseWrapper<T> extends BaseModel {
    private Response response;
    private Integer requestCode;


    public RetrofitResponseWrapper(Parcel in, Response response, Integer requestCode) {
        super(in);
        this.response = response;
        this.requestCode = requestCode;
    }

    public RetrofitResponseWrapper(Response response, Integer requestCode) {
        this.response = response;
        this.requestCode = requestCode;
    }

    public Response getRetrofitResponse() {
        return response;
    }

    public void setRetrofitError(Response response) {
        this.response = response;
    }

    public Integer getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(Integer requestCode) {
        this.requestCode = requestCode;
    }
}
