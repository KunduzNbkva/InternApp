package com.example.internapp.ui.comments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.internapp.R;
import com.example.internapp.adapter.CommentsAdapter;
import com.example.internapp.model.CommentModel;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "id";
    private RecyclerView listComments;
    private CommentsAdapter commentsAdapter;
    private List<CommentModel> list = new ArrayList<>();
    private CommentViewModel viewModel;
    private Toolbar toolbar;
    private EditText username,email,comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initViews();
    }

    public void initViews() {
        toolbar = findViewById(R.id.toolbar_comment);
        viewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        observeComments();
        viewModel.getComments(getIntent().getIntExtra(EXTRA_ID, 0));
        createRecycler();
        onBackClick();
    }

    public void observeComments() {
        viewModel.comments.observe(this, commentModels -> {
            list.addAll(commentModels);
            commentsAdapter.notifyDataSetChanged();
        });
    }

    public void createRecycler(){
        listComments = findViewById(R.id.list_comments);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        listComments.setLayoutManager(manager);
        commentsAdapter = new CommentsAdapter(list);
        listComments.setAdapter(commentsAdapter);
    }

    public void setAlertDialog(){
       AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater inflater= this.getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog,null);
        builder.setView(view)
                .setTitle("Add your comment")
                .setNegativeButton("CANCEL",null)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (username.getText() == null) {
                            Toast.makeText(CommentActivity.this, "Please write your name", Toast.LENGTH_SHORT).show();
                        } else if (email.getText() == null) {
                            Toast.makeText(CommentActivity.this, "Please write your email", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(CommentActivity.this, "Please write your comment", Toast.LENGTH_SHORT).show();
                        }

                        CommentModel model=new CommentModel();
                        model.setName(username.getText().toString());
                        model.setEmail(email.getText().toString());
                        model.setBody(comment.getText().toString());
                        list.add(model);
                        commentsAdapter.notifyDataSetChanged();
                    }
                });
        username=view.findViewById(R.id.input_name);
        email=view.findViewById(R.id.input_email);
        comment=view.findViewById(R.id.input_comment);
        builder.create().show();
    }

    public void onBackClick() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.add)  setAlertDialog();
        return true;
    }
}