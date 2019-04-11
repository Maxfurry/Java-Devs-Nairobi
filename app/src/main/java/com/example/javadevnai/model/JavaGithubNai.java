package com.example.javadevnai.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class JavaGithubNai implements Parcelable {
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

    protected JavaGithubNai(Parcel in) {
        htmlUrl = in.readString();
        avatarUrl = in.readString();
        name = in.readString();
        username = in.readString();
        followers = in.readInt();
        following = in.readInt();
        publicPepos = in.readInt();
        bio = in.readString();
        company = in.readString();
    }

    public static final Creator<JavaGithubNai> CREATOR = new Creator<JavaGithubNai>() {
        @Override
        public JavaGithubNai createFromParcel(Parcel in) {
            return new JavaGithubNai(in);
        }

        @Override
        public JavaGithubNai[] newArray(int size) {
            return new JavaGithubNai[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(htmlUrl);
        dest.writeString(avatarUrl);
        dest.writeString(name);
        dest.writeString(username);
        dest.writeInt(followers);
        dest.writeInt(following);
        dest.writeInt(publicPepos);
        dest.writeString(bio);
        dest.writeString(company);
    }
}
