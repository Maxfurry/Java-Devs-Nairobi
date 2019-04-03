package com.example.javadevnai.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.javadevnai.R;
import com.example.javadevnai.adapter.GithubAdapter;
import com.example.javadevnai.model.JavaGithubNai;
import com.example.javadevnai.presenter.GithubPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements JavaGithubAllUserView {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    GithubPresenter presenter;

    Context context = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        presenter = new GithubPresenter(this);
        presenter.getAllJavaUser();
    }

    @Override
    public void displayAllJavaUsers(List<JavaGithubNai> javaGithubNaiUsers) {
        GithubAdapter adapter = new GithubAdapter(context, javaGithubNaiUsers);
        recyclerView.setAdapter(adapter);
    }
}
