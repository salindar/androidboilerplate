package com.salinda.androidboilerplate.analytics.core;

import com.salinda.androidboilerplate.analytics.event.AnalyticEventBase;
import com.salinda.androidboilerplate.ui.activity.BaseActivity;

import java.lang.ref.WeakReference;

public interface Analytic {
    public abstract void initialize(WeakReference<BaseActivity> baseActivity);
    public abstract void trackEvent(AnalyticEventBase analyticEventBases);
}
