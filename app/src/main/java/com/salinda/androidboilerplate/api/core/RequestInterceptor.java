package com.salinda.androidboilerplate.api.core;

import android.os.Bundle;

/**
 * Created by Salinda on 10/12/15.
 */
public class RequestInterceptor implements retrofit.RequestInterceptor {
    private Bundle bundle;

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void intercept(RequestFacade request) {
        populateHeaders(request);
    }

    private void populateHeaders(RequestFacade request) {
        if (bundle != null && bundle.keySet() != null) {
            for (String key : bundle.keySet()) {
                request.addHeader(key, bundle.getString(key));
            }
        }
    }
}
