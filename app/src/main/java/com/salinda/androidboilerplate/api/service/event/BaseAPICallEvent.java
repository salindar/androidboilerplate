package com.salinda.androidboilerplate.api.service.event;

/**
 *
 */
public class BaseAPICallEvent {
    private String baseUrl;
    private boolean showProgress = Boolean.TRUE;

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public BaseAPICallEvent(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private BaseAPICallEvent() {

    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
