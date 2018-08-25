package com.salinda.androidboilerplate.core;

import android.content.Context;

import com.salinda.androidboilerplate.R;
import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.squareup.otto.Bus;

/**
 * Created by Salinda
 */
public class GuiceModule implements Module {
    private Context context;

    @Inject
    public GuiceModule(Context context) {
        this.context = context;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(Bus.class).toProvider(BusProvider.class).in(Singleton.class);
        binder.bind(String.class).annotatedWith(Names.named("api_base_url")).toInstance(context.getString(R.string.api_base_url));
    }
}
