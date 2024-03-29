package com.example.githubusers.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("PMD")
public class GithubUsersResponse {

    @SerializedName("items")
    private List<GithubUsers> users;

    //Constructors
    public GithubUsersResponse(List<GithubUsers> users) {
        this.users = users;
    }
    public GithubUsersResponse() {} //Pmd Complains

    //Getter & Setters
    public List<GithubUsers> getUsers() {
        return users;
    }
    public void setUsers(List<GithubUsers> users){
        this.users = users;
    }

}
