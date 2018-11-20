package com.salinda.androidboilerplate.ui;

import android.app.Application;


import com.salinda.androidboilerplate.api.service.impl.APIService;
import com.salinda.androidboilerplate.core.DaggerMyComponent;
import com.salinda.androidboilerplate.core.RandomInjectionModule;
import com.salinda.androidboilerplate.core.MyComponent;
import com.squareup.otto.Bus;



/**
 * Created by Salinda
 */
public class App extends Application {
   // @Inject
    private Bus bus;

   // @Inject
    private APIService APIService;
    private static MyComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        initializeGuice();
        registerServicesInBus();
        component = DaggerMyComponent.builder().build();
    }
    public static MyComponent getMyComponent() {
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
