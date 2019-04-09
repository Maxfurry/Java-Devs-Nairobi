package com.example.javadevnai.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JavaGithubResponse {
    @SerializedName("items")
    List<JavaGithubNai> githubUsers;

    public List<JavaGithubNai> getGithubUserDetail() {
        return githubUsers;
    }

    public static JavaGithubResponse parseJSON(String response) {
        JavaGithubResponse getGithubUserDetail;
        Gson gson = new GsonBuilder().create();
        getGithubUserDetail = gson.fromJson(response, JavaGithubResponse.class);
        return getGithubUserDetail;
    }
}
