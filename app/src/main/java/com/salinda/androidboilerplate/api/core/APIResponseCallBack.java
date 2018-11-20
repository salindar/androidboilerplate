package com.salinda.androidboilerplate.api.core;

import com.salinda.androidboilerplate.model.RetrofitErrorWrapper;
import com.salinda.androidboilerplate.model.RetrofitResponseWrapper;
import com.salinda.androidboilerplate.ui.App;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Salinda
 */
//TODO add class level javadoc
public class APIResponseCallBack<T> implements Callback<T> {
    public EventBus getBus() {
        return bus;
    }

    public void setBus(EventBus bus) {
        this.bus = bus;
    }

    //TODO use dagger 2 injection
    //Try to inject this using dagger
    private EventBus bus;
    private int requestCode = 0;


    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()){
            bus.post(new RetrofitResponseWrapper<T>(response, requestCode));
        }else{
            RetrofitErrorWrapper retrofitErrorWrapper = new RetrofitErrorWrapper();
            retrofitErrorWrapper.setRequestCode(requestCode);
            bus.post(retrofitErrorWrapper);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
int x=0;
    }
}
