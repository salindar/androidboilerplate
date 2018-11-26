package com.salinda.androidboilerplate.analytics;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.salinda.androidboilerplate.analytics.core.Analytic;
import com.salinda.androidboilerplate.analytics.event.AnalyticEventBase;
import com.salinda.androidboilerplate.ui.activity.BaseActivity;

import java.lang.ref.WeakReference;

public class FireBaseAnalytics implements Analytic {
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    public void initialize(WeakReference<BaseActivity> baseActivity) {
        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = FirebaseAnalytics.getInstance(baseActivity.get().getApplicationContext());
    }

    @Override
    public void trackEvent(AnalyticEventBase analyticEventBases) {
        if (analyticEventBases == null || analyticEventBases.getEventName() == null) {
            //No interested Analytics frameworks available hence nothing to initialize
            throw new RuntimeException("Analytic event received but event name is missing please mention the event name in AnalyticEventBase");
        }
        firebaseAnalytics.logEvent(analyticEventBases.getEventName(), analyticEventBases.getEventProperties());
    }
}
