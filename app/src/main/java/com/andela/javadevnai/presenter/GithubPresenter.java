package com.andela.javadevnai.presenter;

import android.util.Log;

import com.andela.javadevnai.model.JavaGithubNai;
import com.andela.javadevnai.model.JavaGithubResponse;
import com.andela.javadevnai.service.GithubService;
import com.andela.javadevnai.view.JavaGithubAllUserView;
import com.andela.javadevnai.view.JavaGithubUserView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {
    private GithubService githubService;
    JavaGithubAllUserView javaGithubAllUserView;
    JavaGithubUserView javaGithubUserView;

    public GithubPresenter(JavaGithubUserView view) {
        this.javaGithubUserView = view;

        if(githubService == null) {
            githubService = new GithubService();
        }
    }

    public GithubPresenter(JavaGithubAllUserView view) {
        this.javaGithubAllUserView = view;

        if(githubService == null) {
            githubService = new GithubService();
        }
    }

    public void getJavaUser(String username) {
        githubService
                .getAPI()
                .getSpecificJavaUser(username)
                .enqueue(new Callback<JavaGithubNai>() {
                    @Override
                    public void onResponse(Call<JavaGithubNai> call, Response<JavaGithubNai> response) {
                        JavaGithubNai data = response.body();
                        javaGithubUserView.displayJavaUser(data);
                    }

                    @Override
                    public void onFailure(Call<JavaGithubNai> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            Log.e("API_Call", e.toString());
                        }
                    }
                });
    }

    public void getAllJavaUser() {
        githubService
                .getAPI()
                .getAllJavaUsers()
                .enqueue(new Callback<JavaGithubResponse>() {
                    @Override
                    public void onResponse(Call<JavaGithubResponse> call, Response<JavaGithubResponse> response) {
                        JavaGithubResponse data = response.body();

                        if (data != null && data.getGithubUserDetail() != null) {
                            List<JavaGithubNai> javaGithubUser = data.getGithubUserDetail();
                            javaGithubAllUserView.displayAllJavaUsers(javaGithubUser);
                        }
                    }

                    @Override
                    public void onFailure(Call<JavaGithubResponse> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            Log.e("API_Call", e.toString());
                        }
                    }
                });
    }
}
