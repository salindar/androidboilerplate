package com.salinda.androidboilerplate.model;

import retrofit.RetrofitError;

/**
 * Created by Salinda
 */
public class RetrofitErrorWrapper extends BaseModel {
    private RetrofitError retrofitError;
    private Integer requestCode;

    public RetrofitErrorWrapper() {
        super();
    }

    public RetrofitError getRetrofitError() {
        return retrofitError;
    }

    public void setRetrofitError(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }

    public Integer getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(Integer requestCode) {
        this.requestCode = requestCode;
    }
}
