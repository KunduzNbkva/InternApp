package com.example.internapp.ui.comments;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.internapp.App;
import com.example.internapp.data.PostApiClient;
import com.example.internapp.model.CommentModel;
import java.util.List;

public class CommentViewModel extends ViewModel {
    public MutableLiveData<List<CommentModel>> comments = new MutableLiveData<>();

    public void getComments(int id){
        App.service.getComments(new PostApiClient.CommentsCallback<List<CommentModel>>() {
            @Override
            public void onSuccess(List<CommentModel> model) {
                comments.setValue(model);
            }
            @Override
            public void onFailure(Exception e) {
            }
        },id);
    }
}
