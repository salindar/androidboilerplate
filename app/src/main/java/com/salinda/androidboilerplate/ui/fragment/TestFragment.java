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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.salinda.androidboilerplate.R;
import com.salinda.androidboilerplate.analytics.core.Analytic;
import com.salinda.androidboilerplate.analytics.event.AnalyticConstant;
import com.salinda.androidboilerplate.analytics.event.AnalyticEventBase;
import com.salinda.androidboilerplate.analytics.event.SampleAnalyticEvent;
import com.salinda.androidboilerplate.api.service.event.SampleAPIEvent;
import com.salinda.androidboilerplate.api.service.impl.APIService;
import com.salinda.androidboilerplate.model.SampleModel;
import com.salinda.androidboilerplate.ui.App;
import com.salinda.androidboilerplate.viewmodel.TestFragmentViewModel;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * Created by: Salinda
 */
public class TestFragment extends BaseFragment {
    SwipeRefreshLayout swipeRefreshLayout;
    private TestFragmentViewModel model;
    private Button button;
    private Button button1;
    private TextView test;
    private ProgressBar progressBar;

    @Inject
    APIService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        /**
         * View initializing should be happen in this callback in order framework to
         * be worked properly
         */
        swipeRefreshLayout = view.findViewById(R.id.fr_swipe_refresh);
        button = view.findViewById(R.id.button);
        button1 = view.findViewById(R.id.button1);
        test = view.findViewById(R.id.test);
        progressBar = view.findViewById(R.id.progressBar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*SEND AN EVENT TO ANALYTICS This is how we send and Analytic event to the Analytic
                frameworks. If we post a POJO which is a sub class of AnalyticEventBase AnalyticManager will take care of
                of the passing that event to the analytic framework which registered in AnalyticManager class.
                 */
                Bundle params = new Bundle();
                params.putString("from_where", "Test Fragment");
                bus.post(new SampleAnalyticEvent(AnalyticConstant.SAMPLE_EVENT, params));
                /*END OF SEND AN EVENT TO ANALYTICS*/

                /*
                DO AN API CALL This is how we do an API call, just need to post POJO(wrapped with the data needed for your API call) which is a sub class of
                BaseAPICallEvent and framework will add loading behaviour and result parsing functionality
                for you. Please refer APIService class for further details. This event post will be received by
                the getUser() of APIService.
                 */
                bus.post(new SampleAPIEvent(getResources().getString(R.string.base_url)));
                /*
                END OF DO AN API CALL */

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throw new RuntimeException("Imitating a crash");
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * DEPENDENCY INJECTION
         * If we want to inject dependencies to any class we need to mention that particular class in DaggerComponents
         * and we need to add below line to the constructor of that particular class.
         * Then dependencies will automatically add to that class.
         */
        App.getMyComponent().inject(this);

        /**
         * END OF DEPENDENCY INJECTION **/
    }

    @Override
    protected ProgressBar getProgressView() {
        return progressBar;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        model.getComments().observe(this, observer);

        if (model.getSampleModel() != null) {
            test.setText(model.getSampleModel().getTitle());
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        bus.post(new SampleAPIEvent(getResources().getString(R.string.base_url)));
    }

    @Override
    public String getActivityTitle() {
        return "Demo Fragment";
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

    @Override
    public void registerComponents() {
        super.registerComponents();
        model.register();
        apiService.register();
    }

    @Override
    public void unRegisterComponents() {
        super.unRegisterComponents();
        model.unregister();
        apiService.unregister();
    }

}
