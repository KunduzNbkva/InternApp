package com.example.internapp.data;

import com.example.internapp.model.CommentModel;
import com.example.internapp.model.PostModel;
import java.util.List;

public interface PostApiClient {

    void getPosts(PostsCallback<List<PostModel>> callback);

    void getComments(CommentsCallback<List<CommentModel>> callback,int id);

    interface PostsCallback<T> {
        void onSuccess(List<PostModel> result);
        void onFailure(Exception e);
    }
    interface CommentsCallback<T>{
        void onSuccess(List<CommentModel> model);
        void onFailure(Exception e);
    }
}
