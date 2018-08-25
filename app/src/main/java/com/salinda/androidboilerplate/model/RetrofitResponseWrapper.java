package com.salinda.androidboilerplate.model;

import android.os.Parcel;

import retrofit.client.Response;

/**
 * Created by Salinda.
 */
public class RetrofitResponseWrapper<T> extends BaseModel {
    private Response response;
    private Integer requestCode;
    private T data;


    public RetrofitResponseWrapper(Parcel in, Response response, Integer requestCode) {
        super(in);
        this.response = response;
        this.requestCode = requestCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RetrofitResponseWrapper(Response response, Integer requestCode, T data) {
        this.response = response;
        this.requestCode = requestCode;
        this.data=data;

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
