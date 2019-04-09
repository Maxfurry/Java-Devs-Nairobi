package com.example.javadevnai.service;

import com.example.javadevnai.model.JavaGithubNai;
import com.example.javadevnai.model.JavaGithubResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {
    @GET("/search/users?q=location:nairobi+language:java&page=1&per_page=16")
    Call<JavaGithubResponse> getAllJavaUsers();

    @GET("/users/{username}?access_token=e1edc21b32f0d212716f5c06db14b1d77f44eb6e")
    Call<JavaGithubNai> getSpecificJavaUser(@Path("username") String username);
}
