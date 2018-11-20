package com.salinda.androidboilerplate.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.salinda.androidboilerplate.api.core.RetrofitModule;
import com.salinda.androidboilerplate.api.service.abstractservice.API;
import com.salinda.androidboilerplate.api.service.event.SampleEvent;
import com.salinda.androidboilerplate.api.service.impl.APIService;
import com.salinda.androidboilerplate.core.Person;
import com.salinda.androidboilerplate.model.Comment;
import com.salinda.androidboilerplate.model.RetrofitResponseWrapper;
import com.salinda.androidboilerplate.model.SampleModel;
import com.salinda.androidboilerplate.ui.App;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

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

    //Need to mention every inject class in MyComponent
    TestFragmentViewModel(){
        App.getMyComponent().inject(this);
    }
    private MutableLiveData<SampleModel> comments;
    private SampleModel sampleModel=null;

    public LiveData<SampleModel> getComments() {
        if (comments == null) {
            comments = new MutableLiveData<SampleModel>();
            loadComments();
        }
        return comments;
    }

    @Subscribe
    public void getUser(SampleEvent event) {
        int x=0;
        int y=0;

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

    private void loadComments() {
        // Do an asynchronous operation to fetch comments.
    }

    public  void unregister(){
        bus.unregister(this);
    }

    public  void register(){
        bus.register(this);
    }
}
