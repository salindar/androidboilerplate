package com.salinda.androidboilerplate.api.core;

import com.salinda.androidboilerplate.model.RetrofitErrorWrapper;
import com.salinda.androidboilerplate.model.RetrofitResponseWrapper;
import com.google.inject.Inject;
import com.squareup.otto.Bus;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Salinda
 */
public class APIResponseCallBack<T> implements Callback<T> {
    @Inject
    private Bus bus;
    private int requestCode = 0;

    @Override
    public void success(T dataObject, Response response) {
        bus.post(new RetrofitResponseWrapper<T>(response, requestCode, dataObject));
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    @Override
    public void failure(RetrofitError error) {
        RetrofitErrorWrapper retrofitErrorWrapper = new RetrofitErrorWrapper();
        retrofitErrorWrapper.setRequestCode(requestCode);
        retrofitErrorWrapper.setRetrofitError(error);
        bus.post(retrofitErrorWrapper);
    }
}
