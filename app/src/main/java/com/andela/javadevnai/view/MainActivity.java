package com.andela.javadevnai.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.andela.javadevnai.R;
import com.andela.javadevnai.adapter.GithubAdapter;
import com.andela.javadevnai.model.JavaGithubNai;
import com.andela.javadevnai.presenter.GithubPresenter;
import com.andela.javadevnai.util.NetworkConnection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JavaGithubAllUserView {

    private RecyclerView recyclerView;

    GithubPresenter presenter;

    Context context = this;

    ProgressBar progressBar;

    ConstraintLayout loader;

    SwipeRefreshLayout swipeRefreshLayout;

    List<JavaGithubNai> mGithubUsers;

    private static final String LIST_STATE = "listState";
    private Parcelable mListState = null;

    RecyclerView.LayoutManager layoutManager;

    List<JavaGithubNai> savedGithubUsers;
    private static final String GITHUB_USERS = "java_github_users";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        int orientation = this.getResources().getConfiguration().orientation;
        presenter = new GithubPresenter(this);

        layoutManager = new GridLayoutManager(context, getResources().getInteger(R.integer.grid_count_portrait));

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            this.setGridCount(getResources().getInteger(R.integer.grid_count_portrait));
        } else {
            this.setGridCount(getResources().getInteger(R.integer.grid_count_landscape));
        }

        if(savedInstanceState == null) {
            this.loadGithubUsers();
        }

        progressBar = (ProgressBar) findViewById(R.id.loadingdata_progress);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);

        loader = findViewById(R.id.loader);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadGithubUsers();
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
        if (item.getItemId() == R.id.refresh_page) {
            swipeRefreshLayout.setRefreshing(true);
            this.loadGithubUsers();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayAllJavaUsers(List<JavaGithubNai> javaGithubNaiUsers) {
        mGithubUsers = javaGithubNaiUsers;
        savedGithubUsers = (ArrayList<JavaGithubNai>)javaGithubNaiUsers;

        GithubAdapter adapter = new GithubAdapter(context, javaGithubNaiUsers);
        recyclerView.setAdapter(adapter);
        loader.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void setGridCount(int gridCount) {
        ((GridLayoutManager) layoutManager).setSpanCount(gridCount);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT) {
            this.setGridCount(getResources().getInteger(R.integer.grid_count_portrait));
        }else if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            this.setGridCount(getResources().getInteger(R.integer.grid_count_landscape));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Type listType = new TypeToken<List<JavaGithubNai>>() {}.getType();
        String serializedJavaGithubUsers = new Gson().toJson(mGithubUsers, listType);
        SharedPreferences githubUsers = getSharedPreferences("JavaGithubUsers", MODE_PRIVATE);
        SharedPreferences.Editor editor = githubUsers.edit();
        editor.putString("javaGithubUsers", serializedJavaGithubUsers);
        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putParcelableArrayList(GITHUB_USERS, (ArrayList<JavaGithubNai>) this.savedGithubUsers);
        mListState = layoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE, mListState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        if (state != null) {
            savedGithubUsers = state.getParcelableArrayList(GITHUB_USERS);
            mListState = state.getParcelable(LIST_STATE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Type listType = new TypeToken<List<JavaGithubNai>>() {}.getType();
        SharedPreferences githubUsers = getSharedPreferences("JavaGithubUsers", MODE_PRIVATE);
        String serializedJavaGithubUsers = githubUsers.getString("javaGithubUsers", null);
        List<JavaGithubNai> javaGithubUserList = new Gson().fromJson(serializedJavaGithubUsers, listType);
        if (javaGithubUserList != null) {
            displayAllJavaUsers(javaGithubUserList);
        }

        if (mListState != null) {
            layoutManager.onRestoreInstanceState(mListState);
            mListState = null;
        }
    }

    private class NoInternetSnackBarListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            loadGithubUsers();
        }
    }

    public void loadGithubUsers() {
        if (new NetworkConnection(this).isConnected()) {
            presenter.getAllJavaUser();
        } else {
            Snackbar.make(findViewById(R.id.main_activity), R.string.no_internet,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry, new NoInternetSnackBarListener())
                    .show();
        }
    }
}
