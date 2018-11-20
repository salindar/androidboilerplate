package com.salinda.androidboilerplate.core;

/**
 * Created by: Salinda Rathnayeka on 27/08/2018.
 * Email: salindakrishantha@gmail.com
 */
import javax.inject.Inject;

public class Body {

    @Inject
    Blood blood;


    @Inject
    public Body(){}

    public Blood getBlood() {
        return blood;
    }

    public void setBlood(Blood blood) {
        this.blood = blood;
    }

}
