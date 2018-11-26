package com.salinda.androidboilerplate.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.salinda.androidboilerplate.R;
import com.salinda.androidboilerplate.analytics.core.AnalyticManager;
import com.salinda.androidboilerplate.api.service.event.BaseAPICallEvent;
import com.salinda.androidboilerplate.model.RetrofitErrorWrapper;
import com.salinda.androidboilerplate.model.RetrofitResponseWrapper;
import com.salinda.androidboilerplate.ui.App;
import com.salinda.androidboilerplate.ui.FragmentInteraction;
import com.salinda.androidboilerplate.ui.activity.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;


/**
 * Created by Salinda
 */
public abstract class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, EventBusHandler {
    protected FragmentInteraction callBack;
    private android.app.ActionBar actionBar;

    @Inject
    protected EventBus bus;
    @Inject
    AnalyticManager analyticManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getMyComponent().inject(this);
        actionBar = ((BaseActivity) getActivity()).getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //one time initialization of analytics frameworks.
        analyticManager.initialize((BaseActivity) getActivity());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getSwipeRefreshLayout() != null) {
            setSwipeToRefreshListeners(getSwipeRefreshLayout());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.registerComponents();
        if (actionBar != null) {
            actionBar.setTitle(getActivityTitle());
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        this.unRegisterComponents();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callBack = (FragmentInteraction) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onFragmentInteraction");
        }
    }

    /*
    Mention the actionbar title if if need to be changed.
     */
    public abstract String getActivityTitle();

    //CUSTOM INTERFACE IMPLEMENTATIONS*************************************************************
    @Override
    public void onRefresh() {
        handleViewRefresh(true);
    }

    @Override
    public void registerComponents() {
        bus.register(this);
        analyticManager.registerForEventBus();
    }

    @Override
    public void unRegisterComponents() {
        bus.unregister(this);
        analyticManager.unregisterForomEventBus();
    }

    //CUSTOM INTERFACE IMPLEMENTATIONS*************************************************************

    /**
     * Implement this method and return a View instance
     * if the sub fragments need to show any progress indication when performing an API call.
     * So the framework will handle showing and hiding provided View. If some fragment does not
     * need this functionality for some reason just return null from the method.
     *
     * @return
     */
    protected abstract View getProgressView();

    private void showProgressBar(boolean visible) {
        View progressView = getProgressView();
        if (progressView != null) {
            if (visible) {
                progressView.setVisibility(View.VISIBLE);
            } else {
                progressView.setVisibility(View.INVISIBLE);
            }

        }
    }

    protected abstract SwipeRefreshLayout getSwipeRefreshLayout();


    protected void setSwipeToRefreshListeners(SwipeRefreshLayout layout) {
        layout.setOnRefreshListener(this);
        layout.setColorSchemeResources(R.color.swipe_color_1, R.color.swipe_color_2, R.color.swipe_color_3);
    }

    protected void startDataFetchOnStartUp() {
        SwipeRefreshLayout swipeRefreshLayout = getSwipeRefreshLayout();
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    handleViewRefresh(true);
                }
            });
        }
    }

    protected void handleViewRefresh(boolean refreshStarted) {
        if (getSwipeRefreshLayout() != null) {
            getSwipeRefreshLayout().setRefreshing(refreshStarted);
            onRefreshingView(!refreshStarted, getSwipeRefreshLayout());
        }
    }

    private void onRefreshingView(boolean enable, ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup) {
                onRefreshingView(enable, (ViewGroup) child);
            }
        }
    }

    protected void handleBackPress() {
        if (getSwipeRefreshLayout() != null && getSwipeRefreshLayout().isRefreshing()) {
            handleViewRefresh(false);
        } else {
            if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getActivity().getSupportFragmentManager().popBackStack();
            } else {
                getActivity().finish();
            }
        }
    }

    protected void showErrorMessage(final String errorMessage) {
        if (errorMessage != null && getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    //EVENT SUBSCRIPTIONS***************************************************************************
    /**
     * This method will listen for retrofit errors and show error and disable any indicators such as
     * loading views and refreshing view if available.
     * @param retrofitErrorWrapper
     */
    @Subscribe
    protected void handleRetrofitError(RetrofitErrorWrapper retrofitErrorWrapper) {
        String errorMessage = null;
        switch (retrofitErrorWrapper.getErrorType()) {
            case NETWORK:
                errorMessage = "It seems network unreachable.";
                break;
            case UNEXPECTED:

                break;

            default:
                break;
        }

        if (errorMessage != null) {
            showErrorMessage(errorMessage);
        }
        handleViewRefresh(false);
        showProgressBar(false);
    }


    /**
     * This method will listen for API events and show the progress bar if needed
     *
     * @param baseEvent
     */
    @Subscribe
    public void listenForApiEvent(BaseAPICallEvent baseEvent) {
        if (baseEvent.isShowProgress()) {
            showProgressBar(true);
        }
    }

    /**
     * This method will listen for success API callbacks and disable if progress
     * view is showing. Also this will disable refreshing indicators if available.
     *
     * @param retrofitResponseWrapper
     */
    @Subscribe
    public void listenForApiResult(RetrofitResponseWrapper retrofitResponseWrapper) {
        showProgressBar(false);
        handleViewRefresh(false);
    }

    //END OF EVENT SUBSCRIPTIONS********************************************************************
}
