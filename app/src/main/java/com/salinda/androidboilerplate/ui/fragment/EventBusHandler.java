package com.salinda.androidboilerplate.ui.fragment;

/**The implementer of this interface is responsible for register and unregister java classes which require
 * EvenBus for event subscription in particular java classes. EventBus subscriptions of fragments and activities are
 * automatically handled.
 * Created by: Salinda Rathnayeka on 06/11/2018.
 * Email: salindakrishantha@gmail.com
 */
public interface EventBusHandler {
    /**
     * Required components need to be mentioned in the Fragment(implementer) class and
     * their EvenBus register method should be called inside this method
     */
    public abstract void registerComponents();

    /**
     * Required components need to be mentioned in the Fragment(implementer) class and
     * their EvenBus unRegister method should be called inside this method
     */
    public abstract void unRegisterComponents();
}
