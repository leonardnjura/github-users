package com.example.githubusers.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GithubUsersResponse {

    @SerializedName("items")
    private List<GithubUsers> users;

    //Constructor
    public GithubUsersResponse(List<GithubUsers> users) {
        this.users = users;
    }

    //Getter & Setters
    public List<GithubUsers> getUsers() {
        return users;
    }
    public void setUsers(List<GithubUsers> users){
        this.users = users;
    }

}
