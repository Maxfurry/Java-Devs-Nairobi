package com.example.javadevnai.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javadevnai.R;
import com.example.javadevnai.model.JavaGithubNai;
import com.example.javadevnai.presenter.GithubPresenter;
import com.squareup.picasso.Picasso;

public class DevDetails extends AppCompatActivity implements JavaGithubUserView {

    Context context = this;

    ImageView avatar;
    TextView username;
    TextView fullname;
    TextView githubUrl;
    TextView bio;
    TextView followers;
    TextView followings;
    TextView repo;
    TextView organisation;

    GithubPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        avatar = this.findViewById(R.id.display_pic);
        username = this.findViewById(R.id.username);
        fullname = this.findViewById(R.id.full_name);
        githubUrl = this.findViewById(R.id.githubUrl);
        bio = this.findViewById(R.id.bio);
        followers = this.findViewById(R.id.followers);
        followings = this.findViewById(R.id.following);
        repo = this.findViewById(R.id.repositories);
        organisation = this.findViewById(R.id.organisation);

        presenter = new GithubPresenter(this);
        String username = getIntent().getExtras().getString("username");
        presenter.getJavaUser(username);
        Log.d("Name", username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_dev_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Toast.makeText(context, "You clicked the share button", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void displayJavaUser(JavaGithubNai javaGithubNaiUser) {
        Picasso.get().load(javaGithubNaiUser.getAvatarUrl()).into(avatar);
        username.setText(javaGithubNaiUser.getUsername());
        fullname.setText(javaGithubNaiUser.getName());

        followers.setText(javaGithubNaiUser.getFollowers()+"");
        followings.setText(javaGithubNaiUser.getFollowing()+"");
        repo.setText(javaGithubNaiUser.getRepo()+"");
        githubUrl.setText(javaGithubNaiUser.getHtmlUrl());

        String info = javaGithubNaiUser.getBio();
        String company = javaGithubNaiUser.getCompany();

        if (company != null) {
            organisation.setText("\u2605 " + company);
        } else {
            organisation.setText("N/A");
        }

        if (company != null) {
            bio.setText(info);
        } else {
            bio.setText("N/A");
        }
    }
}
