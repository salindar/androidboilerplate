package com.salinda.androidboilerplate.api.service.impl;

import com.salinda.androidboilerplate.api.core.RetrofitModule;
import com.salinda.androidboilerplate.api.service.abstractservice.API;
import com.salinda.androidboilerplate.api.service.event.SampleEvent;
import com.google.inject.Inject;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by Salinda
 */
public class APIService {
    @Inject
    private Bus bus;

    @Inject
    private RetrofitModule<API> retrofitModule;


    @Subscribe
    public void getUser(SampleEvent event) {

    }
}
