package com.andela.javadevnai.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andela.javadevnai.R;
import com.andela.javadevnai.model.JavaGithubNai;
import com.andela.javadevnai.view.DevDetails;
import com.andela.javadevnai.view.MyViewHolder;

import java.util.List;

import com.squareup.picasso.Picasso;

public class GithubAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;

    MyViewHolder viewHolder;
    List <JavaGithubNai> devList;

    public GithubAdapter(Context context, List <JavaGithubNai> devList) {
        this.devList = devList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        JavaGithubNai javaDev = devList.get(position);

        final String imageUrl = javaDev.getAvatarUrl();
        final String username = javaDev.getUsername();
        final String javaGithubURL = javaDev.getHtmlUrl();

        Picasso.get().load(imageUrl).into(holder.avatar);

        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DevDetails.class);
                intent.putExtra("username", username);
                intent.putExtra("avater_url", imageUrl);
                intent.putExtra("html_url", javaGithubURL);
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
