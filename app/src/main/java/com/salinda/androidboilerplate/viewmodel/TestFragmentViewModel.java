package com.salinda.androidboilerplate.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.salinda.androidboilerplate.model.RetrofitResponseWrapper;
import com.salinda.androidboilerplate.model.SampleModel;
import com.salinda.androidboilerplate.ui.App;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * Created by: Salinda Rathnayeka on 25/08/2018.
 * Email: salindakrishantha@gmail.com
 * <p>
 * IMPORTANT This class is for demonstration purpose only can be
 * removed from the codebase if needed
 */
public class TestFragmentViewModel extends ViewModel {
    @Inject
    EventBus bus;

    //Need to mention every inject class in DaggerComponents
    public TestFragmentViewModel()  {
        App.getMyComponent().inject(this);
    }

    private MutableLiveData<SampleModel> comments;
    private SampleModel sampleModel=null;

    public LiveData<SampleModel> getComments() {
        if (comments == null) {
            comments = new MutableLiveData<SampleModel>();
        }
        return comments;
    }

    @Subscribe
    public void getResult(RetrofitResponseWrapper retrofitResponseWrapper) {
        SampleModel sampleModel1=(SampleModel)retrofitResponseWrapper.getRetrofitResponse().body();
        comments.setValue(sampleModel1);
        sampleModel=sampleModel1;

    }

    public SampleModel getSampleModel() {
        return sampleModel;
    }

    public  void unregister(){
        bus.unregister(this);
    }

    public  void register(){
        bus.register(this);
    }
}
