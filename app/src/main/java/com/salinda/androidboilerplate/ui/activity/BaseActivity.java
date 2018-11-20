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



public abstract class BaseActivity extends AppCompatActivity implements FragmentInteraction {
    protected CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getApplicationContext();
    }

    @Override
    protected void onPause() {
        super.onPause();
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

    protected void addFragment(int container, Class clasz, boolean addToBackStack) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        /*update the main content by replacing fragments*/
        BaseFragment baseFragment;
        //In order to handle https://stackoverflow.com/questions/13305861/fool-proof-way-to-handle-fragment-on-orientation-change/13306633#13306633
        baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(clasz.toString());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Use replace instead of add as to fix the but which shows the previous fragment underneath.
        //http://stackoverflow.com/questions/6228401/adding-a-fragment-with-add-method-doesnt-hide-previous-fragments
        if (baseFragment == null) {
            baseFragment = (BaseFragment) clasz.newInstance();
        }
        fragmentTransaction.replace(container, baseFragment, clasz.toString());

        if (addToBackStack) {
            fragmentTransaction.addToBackStack("");
        }
        fragmentTransaction.commit();
    }

    @Override
    public abstract void onFragmentInteraction(Intent intent);


}
