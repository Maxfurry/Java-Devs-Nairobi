package com.example.javadevnai.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javadevnai.R;
import com.example.javadevnai.view.DevDetails;
import com.example.javadevnai.view.MyViewHolder;

public class GithubAdapter extends RecyclerView.Adapter<MyViewHolder> {

    String[] devList;
    Bitmap avatar;
    Context context;

    public GithubAdapter(Context context, String[] devList, Bitmap avatar) {
        this.context = context;
        this.devList = devList;
        this.avatar = avatar;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.avatar.setImageBitmap(avatar);
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DevDetails.class);
                context.startActivity(intent);
            }
        });
        holder.name.setText(devList[holder.getAdapterPosition()]);
    }
    @Override
    public int getItemCount() {
        return devList.length;
    }
}