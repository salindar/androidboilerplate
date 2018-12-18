package com.salinda.androidboilerplate.core;

import com.salinda.androidboilerplate.analytics.core.AnalyticManager;
import com.salinda.androidboilerplate.api.service.impl.APIService;
import com.salinda.androidboilerplate.ui.activity.BaseActivity;
import com.salinda.androidboilerplate.ui.fragment.BaseFragment;
import com.salinda.androidboilerplate.ui.fragment.TestFragment;
import com.salinda.androidboilerplate.viewmodel.TestFragmentViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by: Salinda Rathnayeka on 28/10/2018.
 * Email: salindakrishantha@gmail.com
 */

@Singleton//DAGGER This is must to have singleton providers in RandomInjectionModule
@Component(modules={RandomInjectionModule.class,APICallBackInjectionModule.class})
public interface DaggerComponents {
    //Need to mention every inject class here
    void inject(BaseActivity activity);
    void inject(TestFragmentViewModel viewModel);
    void inject(BaseFragment baseFragment);
    void inject(TestFragment testFragment);
    void inject(APIService apiService);
    void inject(AnalyticManager analyticManager);
}
