package com.example.javadevnai.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    String githubUsername;
    String JavaGithubURL;

    GithubPresenter presenter;

    ProgressBar progressBar;

    ConstraintLayout loader;
    ConstraintLayout dev_detail;

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
        githubUsername = getIntent().getExtras().getString("username");
        presenter.getJavaUser(githubUsername);

        progressBar = (ProgressBar) findViewById(R.id.loadingdata_progress);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);

        loader = findViewById(R.id.loader);
        dev_detail = findViewById(R.id.dev_detail);
        dev_detail.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_dev_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBodyText = String.format("Check out this awesome developer @%s, %s.", githubUsername, JavaGithubURL);
            String shareSubject = "Java Github Developer Nairobi";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);

            startActivity(Intent.createChooser(sharingIntent, "Share via"));
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

        JavaGithubURL = javaGithubNaiUser.getHtmlUrl();
        githubUrl.setText(JavaGithubURL);

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

        loader.setVisibility(View.GONE);
        dev_detail.setVisibility(View.VISIBLE);
    }
}
