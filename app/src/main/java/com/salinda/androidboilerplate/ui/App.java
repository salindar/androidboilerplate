package com.salinda.androidboilerplate.ui;

import android.app.Application;

import com.salinda.androidboilerplate.model.GuiceModule;
import com.google.inject.Inject;
import com.google.inject.util.Modules;
import com.salinda.androidboilerplate.api.service.impl.APIService;
import com.squareup.otto.Bus;

import roboguice.RoboGuice;

/**
 * Created by Salinda
 */
public class App extends Application {
    @Inject
    private Bus bus;

    @Inject
    private APIService APIService;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeGuice();
        registerServicesInBus();
    }

    public Bus getBusObject(){
        return this.bus;
    }

    private void initializeGuice() {
        RoboGuice.getOrCreateBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE, Modules.override(RoboGuice.newDefaultRoboModule(this)).with(new GuiceModule(this)));
    }

    private void registerServicesInBus() {
        bus.register(APIService);
        bus.register(this);
    }
}
