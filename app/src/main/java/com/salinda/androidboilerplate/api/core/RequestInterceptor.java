package com.salinda.androidboilerplate.api.core;

import android.os.Bundle;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by Salinda on 10/12/15.
 */
//TODO add class level javadoc
public class RequestInterceptor implements Interceptor {
    private Bundle bundle;

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    private void populateHeaders(Request.Builder builder) {
        if (bundle != null && bundle.keySet() != null) {
            for (String key : bundle.keySet()) {
                builder.addHeader(key, bundle.getString(key));
            }
        }
    }

    @Override
    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        populateHeaders(builder);
        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }

}
