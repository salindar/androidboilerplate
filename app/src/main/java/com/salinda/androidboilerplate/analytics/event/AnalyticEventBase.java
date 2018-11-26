package com.salinda.androidboilerplate.analytics.event;

import android.os.Bundle;

/**
 * Base class for all the analytic events
 */
public abstract class AnalyticEventBase {
    private String eventName;
    private Bundle eventProperties;

    public AnalyticEventBase(String eventName, Bundle eventProperties) {
        this.eventName = eventName;
        this.eventProperties = eventProperties;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Bundle getEventProperties() {
        return eventProperties;
    }

    public void setEventProperties(Bundle eventProperties) {
        this.eventProperties = eventProperties;
    }
}
