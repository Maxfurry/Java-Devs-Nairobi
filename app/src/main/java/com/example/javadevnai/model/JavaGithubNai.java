package com.example.javadevnai.model;

import com.google.gson.annotations.SerializedName;

public class JavaGithubNai {
    @SerializedName("html_url")
    protected String htmlUrl;
    @SerializedName("avatar_url")
    protected String avatarUrl;
    @SerializedName("name")
    protected String name;
    @SerializedName("login")
    protected String username;
    @SerializedName("followers")
    protected int followers;
    @SerializedName("following")
    protected int following;
    @SerializedName("public_repos")
    protected int publicPepos;
    @SerializedName("bio")
    protected String bio;
    @SerializedName("company")
    protected String company;

    public String getHtmlUrl() {
        return this.htmlUrl;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public int getFollowers() {
        return this.followers;
    }

    public int getFollowing() {
        return this.following;
    }

    public int getRepo() {
        return this.publicPepos;
    }

    public String getBio() {
        return this.bio;
    }

    public String getCompany() {
        return this.company;
    }
}
