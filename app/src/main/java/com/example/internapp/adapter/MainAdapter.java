package com.example.internapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internapp.R;
import com.example.internapp.model.PostModel;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private final List<PostModel> list;
    public OnItemClickListener listener;

    public MainAdapter(List<PostModel> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_posts, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return Math.min(list.size(), 30);
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleText;
        private final TextView descText;
        PostModel model;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title_txt);
            descText = itemView.findViewById(R.id.desc_txt);
            itemView.setOnClickListener(v -> listener.onClick(model.getId()));
        }

        public void onBind(PostModel model) {
            this.model = model;
            titleText.setText(model.getTitle());
            descText.setText(model.getBody());
        }
    }
}
