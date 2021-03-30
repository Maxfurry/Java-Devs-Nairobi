package com.andela.javadevnai.view;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.andela.javadevnai.R;
import com.andela.javadevnai.model.JavaGithubNai;
import com.andela.javadevnai.presenter.GithubPresenter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DevDetails extends AppCompatActivity implements JavaGithubUserView {
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
    String javaGithubURL;
    String imageUrl;

    GithubPresenter presenter;

    ProgressBar progressBar;

    ConstraintLayout loader;
    ConstraintLayout dev_detail;

    JavaGithubNai mGithubUser;

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
        imageUrl = getIntent().getExtras().getString("avater_url");
        javaGithubURL = getIntent().getExtras().getString("html_url");

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
            String shareBodyText = String.format("Check out this awesome developer @%s, %s.", githubUsername, javaGithubURL);
            String shareSubject = "Java Github Developer Nairobi";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);

            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayJavaUser(JavaGithubNai javaGithubNaiUser) {
        mGithubUser = javaGithubNaiUser;

        username.setText(githubUsername);
        Picasso.get().load(imageUrl).into(avatar);
        githubUrl.setText(javaGithubURL);

        if(javaGithubNaiUser != null) {
            fullname.setText(javaGithubNaiUser.getName());

            followers.setText(javaGithubNaiUser.getFollowers() + "");
            followings.setText(javaGithubNaiUser.getFollowing() + "");
            repo.setText(javaGithubNaiUser.getRepo() + "");


            String info = javaGithubNaiUser.getBio();
            String company = javaGithubNaiUser.getCompany();

            if (company != null) {
                organisation.setText("\u2605 " + company);
            } else {
                organisation.setText(R.string.not_available);
            }

            if (company != null) {
                bio.setText(info);
            } else {
                bio.setText(R.string.not_available);
            }
        } else {
            fullname.setText(R.string.not_available);
            followers.setText(R.string.not_available);
            followings.setText(R.string.not_available);
            repo.setText(R.string.not_available);
            organisation.setText(R.string.not_available);
            bio.setText(R.string.not_available);
        }

        loader.setVisibility(View.GONE);
        dev_detail.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String serializedGithubUser = new Gson().toJson(mGithubUser);
        SharedPreferences githubUser = getSharedPreferences("JavaGithubUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = githubUser.edit();
        editor.putString("javaGithubUser", serializedGithubUser);
        editor.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences githubUser = getSharedPreferences("JavaGithubUser", MODE_PRIVATE);
        String serializedGithubUser = githubUser.getString("javaGithubUser", null);
        JavaGithubNai mGithubUser = new Gson().fromJson(serializedGithubUser, JavaGithubNai.class);
        if (mGithubUser != null) {
            displayJavaUser(mGithubUser);
        }
    }
}
