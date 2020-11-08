package com.example.internapp.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.internapp.App;
import com.example.internapp.data.PostApiClient;
import com.example.internapp.model.PostModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    public MutableLiveData<List<PostModel>> posts = new MutableLiveData<>();

    public void getPosts() {
        App.service.getPosts(new PostApiClient.PostsCallback<List<PostModel>>() {
            @Override
            public void onSuccess(List<PostModel> result) {
                posts.setValue(result);
            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
