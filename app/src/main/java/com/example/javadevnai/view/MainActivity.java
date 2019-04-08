package com.example.javadevnai.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

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

    ProgressBar progressBar;

    ConstraintLayout loader;

    SwipeRefreshLayout swipeRefreshLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(context, getResources().getInteger(R.integer.grid_count));
        recyclerView.setLayoutManager(layoutManager);

        presenter = new GithubPresenter(this);
        presenter.getAllJavaUser();

        progressBar = (ProgressBar) findViewById(R.id.loadingdata_progress);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);

        loader = findViewById(R.id.loader);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getAllJavaUser();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_page:
                swipeRefreshLayout.setRefreshing(true);
                presenter.getAllJavaUser();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void displayAllJavaUsers(List<JavaGithubNai> javaGithubNaiUsers) {
        GithubAdapter adapter = new GithubAdapter(context, javaGithubNaiUsers);
        recyclerView.setAdapter(adapter);
        loader.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }
}
