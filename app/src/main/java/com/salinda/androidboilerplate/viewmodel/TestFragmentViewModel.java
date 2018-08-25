package com.salinda.androidboilerplate.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.salinda.androidboilerplate.model.Comment;

import java.util.List;

/**
 * Created by: Salinda Rathnayeka on 25/08/2018.
 * Email: salindakrishantha@gmail.com
 * <p>
 * IMPORTANT This class is for demonstration purpose only can be
 * removed from the codebase if needed
 */
public class TestFragmentViewModel extends ViewModel {
    private MutableLiveData<List<Comment>> comments;

    public LiveData<List<Comment>> getComments() {
        if (comments == null) {
            comments = new MutableLiveData<List<Comment>>();
            loadComments();
        }
        return comments;
    }

    private void loadComments() {
        // Do an asynchronous operation to fetch comments.
    }
}
