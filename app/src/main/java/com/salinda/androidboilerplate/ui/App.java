package com.salinda.androidboilerplate.ui;

import android.app.Application;


import com.salinda.androidboilerplate.api.service.impl.APIService;
import com.salinda.androidboilerplate.core.DaggerComponents;
import com.salinda.androidboilerplate.core.DaggerDaggerComponents;
import com.squareup.otto.Bus;



/**
 * Created by Salinda
 */
public class App extends Application {
   // @Inject
    private Bus bus;

   // @Inject
    private APIService APIService;
    private static DaggerComponents component;
    @Override
    public void onCreate() {
        super.onCreate();
        initializeGuice();
        registerServicesInBus();
        component = DaggerDaggerComponents.builder().build();
    }
    public static DaggerComponents getMyComponent() {
        return component;
    }
    public Bus getBusObject(){
        return this.bus;
    }

    private void initializeGuice() {
        //RoboGuice.getOrCreateBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE, Modules.override(RoboGuice.newDefaultRoboModule(this)).with(new GuiceModule(this)));
    }

    private void registerServicesInBus() {
     //   bus.register(APIService);
     //   bus.register(this);
    }
}
