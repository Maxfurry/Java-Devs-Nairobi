package com.example.javadevnai.service;

import com.example.javadevnai.model.JavaGithubNai;
import com.example.javadevnai.model.JavaGithubResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {
    @GET("/search/users?q=location:nairobi+language:java&page=1&per_page=15")
    Call<JavaGithubResponse> getAllJavaUsers();

    @GET("/users/{username}")
    Call<JavaGithubNai> getSpecificJavaUser(@Path("username") String username);
}
