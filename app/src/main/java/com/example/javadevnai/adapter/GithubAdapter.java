package com.example.javadevnai.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javadevnai.R;
import com.example.javadevnai.model.JavaGithubNai;
import com.example.javadevnai.view.DevDetails;
import com.example.javadevnai.view.MyViewHolder;

import java.util.List;

import com.squareup.picasso.Picasso;

public class GithubAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;

    List <JavaGithubNai> devList;

    public GithubAdapter(Context context, List <JavaGithubNai> devList) {
        this.devList = devList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        JavaGithubNai javaDev = devList.get(position);

        String imageUrl = javaDev.getAvatarUrl();
        final String username = javaDev.getUsername();

        Picasso.get().load(imageUrl).into(holder.avatar);

        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DevDetails.class);
                intent.putExtra("username", username);
                context.startActivity(intent);
            }
        });

        holder.name.setText("@"+username);
    }
    @Override
    public int getItemCount() {
        return devList.size();
    }
}
