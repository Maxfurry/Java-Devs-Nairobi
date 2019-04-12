package com.andela.javadevnai.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andela.javadevnai.R;

public class MyViewHolder extends RecyclerView.ViewHolder{

    public ImageView avatar;
    public TextView name;

    public MyViewHolder(View itemView) {
        super(itemView);
        avatar = (ImageView)itemView.findViewById(R.id.devAvatar);
        name = (TextView)itemView.findViewById(R.id.devName);
    }
}