package com.example.javadevnai.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {
    private Retrofit retrofit = null;

    public GithubAPI getAPI(){
        String API_BASE_URL = "https://api.github.com";

        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GithubAPI.class);
    }
}
