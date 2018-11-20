package com.salinda.androidboilerplate.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.salinda.androidboilerplate.R;
import com.salinda.androidboilerplate.api.service.event.SampleEvent;
import com.salinda.androidboilerplate.api.service.impl.APIService;
import com.salinda.androidboilerplate.model.SampleModel;
import com.salinda.androidboilerplate.ui.App;
import com.salinda.androidboilerplate.viewmodel.TestFragmentViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * Created by: Salinda
 */
public class TestFragment extends BaseFragment {
    SwipeRefreshLayout swipeRefreshLayout;
    private TestFragmentViewModel model;
    private Button button;
    private TextView test;

    @Inject
    APIService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_test, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This is a must to inject components to this class
        App.getMyComponent().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fr_swipe_refresh);
        button=(Button) view.findViewById(R.id.button);
        test=(TextView) view.findViewById(R.id.test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bus.post(new SampleEvent());
            }
        });
    }
    private final Observer<SampleModel> observer = new Observer<SampleModel>() {
        @Override
        public void onChanged(@Nullable SampleModel sampleModel) {
            test.setText(sampleModel.getTitle());
        }
    };


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         model = ViewModelProviders.of(this).get(TestFragmentViewModel.class);

//        model.getComments().observe(this, comments -> {
//            test.setText(comments.getTitle());
//        });
        model.getComments().observe(this,observer);
        if(model.getSampleModel() != null){
            test.setText(model.getSampleModel().getTitle());
        }
    }

    @Override
    public String getActivityTitle() {
        return null;
    }

    @Override
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Subscribe
    public void getUser(SampleEvent event) {
        int x=0;
        int y=0;

    }

    @Override
    public void registerComponents() {
        model.register();
        apiService.register();
    }

    @Override
    public void unRegisterComponents() {
        model.unregister();
        apiService.unregister();
    }
}
