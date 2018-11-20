package com.salinda.androidboilerplate.core;

import dagger.Component;

/**
 * Created by: Salinda Rathnayeka on 27/08/2018.
 * Email: salindakrishantha@gmail.com
 */
@Component( modules = RandomInjectionModule.class )
public interface Doctor {
    Body injectBlood();
    Person injectPerson();
}
