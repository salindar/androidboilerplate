package com.salinda.androidboilerplate.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.salinda.androidboilerplate.R;
import com.salinda.androidboilerplate.model.RetrofitErrorWrapper;
import com.salinda.androidboilerplate.ui.App;
import com.salinda.androidboilerplate.ui.FragmentInteraction;
import com.salinda.androidboilerplate.ui.activity.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;


/**
 * Created by Salinda
 */
public abstract class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,EventBusHandler {
    protected FragmentInteraction callBack;
    private android.app.ActionBar actionBar;
    @Inject
    protected EventBus bus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getMyComponent().inject(this);
        actionBar = ((BaseActivity) getActivity()).getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
        registerComponents();
        if (actionBar != null) {
            actionBar.setTitle(getActivityTitle());
        }

    }


    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
        unRegisterComponents();
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

    public abstract String getActivityTitle();

    @Override
    public void onRefresh() {
        handleViewRefresh(true);
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

    /**
     * Fragment tag is necessary to add fragments to fragment manager otherwise it will cause issues
     * in configuration change. Please provide an unique fragment tag in the implementation fragment.
     * Please refer to the TestFragment class implementation for further details.
     * @return
     */
    public abstract String getFragmentTag();

    protected void handleRetrofitError(RetrofitErrorWrapper retrofitErrorWrapper) {
//        RetrofitError retrofitError = retrofitErrorWrapper.getRetrofitError();
//        String errorMessage = null;
//        switch (retrofitError.getKind()) {
//            case NETWORK:
//                errorMessage = "It seems network unreachable.";
//                break;
//            case UNEXPECTED:
//
//                break;
//            case HTTP:
//                Response response = retrofitError.getResponse();
//                switch (response.getStatus()) {
//                    case 400:
//
//                        break;
//                    case 403:
//
//                        break;
//                    case 404:
//
//                        break;
//                    default:
//
//                }
//                break;
//            default:
//                break;
//        }
//        if (retrofitError != null) {
//            if (errorMessage == null) {
//                errorMessage = retrofitError.getMessage();
//            }
//        }
//        if (errorMessage != null) {
//            showErrorMessage(errorMessage);
//        }
//        handleViewRefresh(false);
    }
}
