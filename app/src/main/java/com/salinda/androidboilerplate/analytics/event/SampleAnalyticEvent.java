package com.salinda.androidboilerplate.analytics.event;

import android.os.Bundle;

public class SampleAnalyticEvent extends AnalyticEventBase {

    public SampleAnalyticEvent(String eventName, Bundle eventProperties) {
        super(eventName, eventProperties);
    }
}
