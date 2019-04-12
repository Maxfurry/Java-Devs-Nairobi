package com.andela.javadevnai.service;

import com.andela.javadevnai.model.JavaGithubNai;
import com.andela.javadevnai.model.JavaGithubResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubAPI {
    @GET("/search/users?q=location:nairobi+language:java&page=1&per_page=18")
    Call<JavaGithubResponse> getAllJavaUsers();

    @GET("/users/{username}")
    Call<JavaGithubNai> getSpecificJavaUser(@Path("username") String username,
                                            @Query("client_id") String CLIENT_ID,
                                            @Query("client_secret") String CLIENT_SECRET);
}
