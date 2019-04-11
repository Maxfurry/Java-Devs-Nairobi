package com.andela.javadevnai.service;

import com.andela.javadevnai.model.JavaGithubNai;
import com.andela.javadevnai.model.JavaGithubResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {
    @GET("/search/users?q=location:nairobi+language:java&page=1&per_page=18")
    Call<JavaGithubResponse> getAllJavaUsers();

    @GET("/users/{username}?access_token=d5814d4993b0d7edfb1ecb19dcfc72c1c45555f5")
    Call<JavaGithubNai> getSpecificJavaUser(@Path("username") String username);
}
