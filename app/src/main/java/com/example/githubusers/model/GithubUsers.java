package com.example.githubusers.model;

import com.google.gson.annotations.SerializedName;


public class GithubUsers {

    @SerializedName("login")
    private String userName;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("url")
    private String profileUrl;

    //Constructor
    public GithubUsers(String userName, String avatarUrl, String profileUrl){
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.profileUrl = profileUrl;

    }

    //Getters & Setters
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getAvatarUrl(){
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }

    public String getProfileUrl(){
        return profileUrl;
    }
    public void setProfileUrl(String profileUrl){
        this.profileUrl = profileUrl;
    }

}
