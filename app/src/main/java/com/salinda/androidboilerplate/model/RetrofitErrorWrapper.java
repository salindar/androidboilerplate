package com.salinda.androidboilerplate.model;

import retrofit2.Response;
/**
 * Created by Salinda
 */
//TODO Evaluate whether this class need to be kept as Retrofit2 does not
    // have RetrofitError class anymore
public class RetrofitErrorWrapper extends BaseModel {
    private Response retrofitError;
    private Integer requestCode;

    public RetrofitErrorWrapper() {
        super();
    }

    public Response getRetrofitError() {
        return retrofitError;
    }

    public void setRetrofitError(Response retrofitError) {
        this.retrofitError = retrofitError;
    }

    public Integer getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(Integer requestCode) {
        this.requestCode = requestCode;
    }
}
