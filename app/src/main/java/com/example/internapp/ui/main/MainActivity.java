package com.example.internapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.internapp.R;
import com.example.internapp.adapter.MainAdapter;
import com.example.internapp.adapter.OnItemClickListener;
import com.example.internapp.model.PostModel;
import com.example.internapp.ui.comments.CommentActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private final List<PostModel> postModelList=new ArrayList<>();
    private MainAdapter adapter;
    private MainViewModel viewModel;
    public RecyclerView postsRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }
    public void initViews(){
        Toolbar toolbar = findViewById(R.id.toolbar_post);
        toolbar.setTitle(R.string.posts);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getPosts();
        observePosts();
        Log.e("TAG","Adapter List "+postModelList);
        adapter=new MainAdapter(postModelList,this);
        postsRecycler = findViewById(R.id.posts_rv);
        postsRecycler.setAdapter(adapter);
    }

    private void observePosts() {
        viewModel.posts.observe(this, postModels -> {
            postModelList.addAll(postModels);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onClick(int position) {
        Intent intent=new Intent(this, CommentActivity.class);
        intent.putExtra(CommentActivity.EXTRA_ID,position);
        startActivity(intent);
    }

}