package com.salinda.androidboilerplate.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.salinda.androidboilerplate.R;
import com.salinda.androidboilerplate.ui.App;
import com.salinda.androidboilerplate.ui.fragment.TestFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "MSG");
        setContentView(R.layout.activity_main);
        //DAGGER This is must to inject required components to the desired component
        App.getMyComponent().inject(this);

        try {
            addFragment(R.id.main_view_content, TestFragment.class, false);
        } catch (ClassNotFoundException e) {

        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        }

    }

    @Override
    public void onFragmentInteraction(Intent intent) {

    }
}
