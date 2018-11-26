package com.salinda.androidboilerplate.analytics.core;

import com.salinda.androidboilerplate.analytics.FireBaseAnalytics;
import com.salinda.androidboilerplate.analytics.event.AnalyticEventBase;
import com.salinda.androidboilerplate.analytics.event.SampleAnalyticEvent;
import com.salinda.androidboilerplate.api.service.event.SampleAPIEvent;
import com.salinda.androidboilerplate.ui.App;
import com.salinda.androidboilerplate.ui.activity.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AnalyticManager {
    private WeakReference<BaseActivity> baseActivityWeakReference;
    private ArrayList<Analytic> analyticArrayList;

    @Inject
    EventBus bus;

    public AnalyticManager() {
        //To initialize injection
        App.getMyComponent().inject(this);
    }

    public void initialize(BaseActivity baseActivity) {
        baseActivityWeakReference = new WeakReference<>(baseActivity);

        List<AnalyticFramework> analyticFrameworks = new ArrayList<>();
        analyticFrameworks.add(AnalyticFramework.FIRE_BASE);

        analyticArrayList = new ArrayList<>();
        for (AnalyticFramework analytics_frameworks : analyticFrameworks) {
            switch (analytics_frameworks) {
                case FIRE_BASE:
                    analyticArrayList.add(new FireBaseAnalytics());
                    break;
                case PYZE:
                    //Add PYZE implementation
                    break;
            }
        }

        for (Analytic analytic : analyticArrayList) {
            analytic.initialize(baseActivityWeakReference);
        }

    }

    @Subscribe
    public void trackEvent(AnalyticEventBase analyticEventBase) {
        if (analyticArrayList == null || analyticArrayList.isEmpty()) {
            throw new RuntimeException(" Analytics frameworks are not initialized properly please initialize them before track events of them.");
        }
        for (Analytic analytic : analyticArrayList) {
            analytic.trackEvent(analyticEventBase);
        }
    }

    public  void registerForEventBus(){
        bus.register(this);
    }

    public  void unregisterForomEventBus(){
        bus.unregister(this);
    }
}
