package com.example.internapp.data;



import androidx.annotation.NonNull;

import com.example.internapp.model.CommentModel;
import com.example.internapp.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class PostApiService implements PostApiClient {
    public final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    PostsApi service = retrofit.create(PostsApi.class);

    @Override
    public void getPosts(PostsCallback<List<PostModel>> callback) {
        service.getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PostModel>> call, @NonNull Response<List<PostModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Response is empty: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull  Call<List<PostModel>> call,@NonNull   Throwable t) {
                callback.onFailure(new Exception(t));
            }
        });
    }

    @Override
    public void getComments(CommentsCallback<List<CommentModel>> callback, int id) {
        service.getComments(id).enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(@NonNull  Call<List<CommentModel>> call, @NonNull  Response<List<CommentModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Response is empty: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull  Call<List<CommentModel>> call,@NonNull   Throwable t) {
                callback.onFailure(new Exception(t));
            }
        });
    }

    interface PostsApi {
        @GET("posts")
        Call<List<PostModel>> getPosts();

        @GET("comments")
        Call<List<CommentModel>> getComments(@Query("id") int id);
    }


}
