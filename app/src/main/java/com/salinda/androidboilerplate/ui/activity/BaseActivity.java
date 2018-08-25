package com.salinda.androidboilerplate.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.salinda.androidboilerplate.ui.App;
import com.salinda.androidboilerplate.ui.FragmentInteraction;
import com.salinda.androidboilerplate.ui.fragment.BaseFragment;
import com.squareup.otto.Bus;



public abstract class BaseActivity extends AppCompatActivity implements FragmentInteraction {
    protected CharSequence mTitle;
    protected Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getApplicationContext();
        bus=((App)this.getApplication()).getBusObject();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    public void setTitle(CharSequence mTitle) {
        if (!TextUtils.isEmpty(mTitle)) {
            this.mTitle = mTitle;
        }
    }

    protected void replaceFragment(int container, BaseFragment fragment) {
    /*update the main content by replacing fragments*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.commit();
    }

    protected void addFragment(int container, BaseFragment fragment, boolean addToBackStack) {
    /*update the main content by replacing fragments*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Use replace instead of add as to fix the but which shows the previous fragment underneath.
        //http://stackoverflow.com/questions/6228401/adding-a-fragment-with-add-method-doesnt-hide-previous-fragments
        fragmentTransaction.replace(container, fragment, "");
        if (addToBackStack) {
            fragmentTransaction.addToBackStack("");
        }
        fragmentTransaction.commit();
    }

    @Override
    public abstract void onFragmentInteraction(Intent intent);


}
