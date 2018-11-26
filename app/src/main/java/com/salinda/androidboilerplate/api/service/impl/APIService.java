package com.salinda.androidboilerplate.api.service.impl;

import com.salinda.androidboilerplate.api.core.APIResponseCallBack;
import com.salinda.androidboilerplate.api.core.RetrofitModule;
import com.salinda.androidboilerplate.api.service.abstractservice.API;
import com.salinda.androidboilerplate.api.service.event.SampleAPIEvent;
import com.salinda.androidboilerplate.model.SampleModel;
import com.salinda.androidboilerplate.ui.App;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by Salinda
 */
//TODO add class level javadoc
public class APIService {
    @Inject
    EventBus bus;
    @Inject
    APIResponseCallBack<SampleModel> sampleModelAPIResponseCallBack;

    @Inject
    RetrofitModule<API> retrofitModule;

    public APIService(){
        App.getMyComponent().inject(this);
    }

    @Subscribe
    public void getUser(SampleAPIEvent event) {
        API api = retrofitModule.getRestClient(API.class, null, null);
        Call<SampleModel> call=api.getUser();
        sampleModelAPIResponseCallBack.setBus(bus);
        call.enqueue(sampleModelAPIResponseCallBack);

    }

    public  void unregister(){
        bus.unregister(this);
    }

    public  void register(){
        bus.register(this);
    }
}
