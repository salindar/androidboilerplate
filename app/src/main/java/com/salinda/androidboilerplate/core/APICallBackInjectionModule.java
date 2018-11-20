package com.salinda.androidboilerplate.core;

import com.salinda.androidboilerplate.api.core.APIResponseCallBack;
import com.salinda.androidboilerplate.api.core.RetrofitModule;
import com.salinda.androidboilerplate.api.service.abstractservice.API;
import com.salinda.androidboilerplate.model.SampleModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by: Salinda Rathnayeka on 27/08/2018.
 * Email: salindakrishantha@gmail.com
 */
@Module
public class APICallBackInjectionModule {

    public APICallBackInjectionModule() {
    }

    @Provides
    static APIResponseCallBack<SampleModel> provideSampleModelAPIResponseCallBack (){
        return new APIResponseCallBack<>();
    }
}
