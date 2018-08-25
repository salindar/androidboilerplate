package com.salinda.androidboilerplate.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.salinda.androidboilerplate.R;
import com.salinda.androidboilerplate.ui.fragment.TestFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG","MSG");
        setContentView(R.layout.activity_main);
        addFragment(R.id.main_view_content,new TestFragment(),false);
    }

    @Override
    public void onFragmentInteraction(Intent intent) {

    }
}
