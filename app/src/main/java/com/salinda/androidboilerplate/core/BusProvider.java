package com.salinda.androidboilerplate.core;

import com.google.inject.Provider;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Salinda
 */
public class BusProvider implements Provider<Bus> {
    @Override
    public Bus get() {
        Bus bus = new Bus(ThreadEnforcer.MAIN);
        return bus;
    }
}
