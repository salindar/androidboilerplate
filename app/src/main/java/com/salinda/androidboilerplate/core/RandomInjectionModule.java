package com.salinda.androidboilerplate.core;

import com.salinda.androidboilerplate.analytics.core.AnalyticManager;
import com.salinda.androidboilerplate.api.core.RetrofitModule;
import com.salinda.androidboilerplate.api.service.abstractservice.API;
import com.salinda.androidboilerplate.api.service.impl.APIService;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by: Salinda Rathnayeka on 27/08/2018.
 * Email: salindakrishantha@gmail.com
 */
@Module
public class RandomInjectionModule {

    public RandomInjectionModule() {
    }

    @Provides
    static RetrofitModule<API>  provideApiModule (){
        return new RetrofitModule<>();
    }

    @Provides
    @Singleton
    static APIService provideApiService(){
        return new APIService();
    }

    @Provides
    @Singleton
    static EventBus getEventBus(){
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    static AnalyticManager getAnalyticManager(){
        return new AnalyticManager();
    }
}
