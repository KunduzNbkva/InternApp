package com.example.internapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internapp.R;
import com.example.internapp.model.CommentModel;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    List<CommentModel> list;

    public CommentsAdapter(List<CommentModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comments, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return Math.min(list.size(), 30);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, email, comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_txt);
            email = itemView.findViewById(R.id.mail_txt);
            comment = itemView.findViewById(R.id.comment_txt);
        }

        public void onBind(CommentModel commentModel) {
            name.setText(commentModel.getName());
            email.setText(commentModel.getEmail());
            comment.setText(commentModel.getBody());

        }
    }
}



