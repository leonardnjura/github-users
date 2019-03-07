package com.example.githubusers.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GithubUsersResponse {

    @SerializedName("items")
    private List<GithubUsers> users;

    public List<GithubUsers> getUsers() {
        return users;
    }

    //Constructor
    public GithubUsersResponse(List<GithubUsers> users) {
        this.users = users;
    }

}
