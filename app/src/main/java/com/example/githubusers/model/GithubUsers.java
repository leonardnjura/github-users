package com.example.githubusers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class GithubUsers implements Parcelable {

    public static final Creator<GithubUsers> CREATOR = new Creator<GithubUsers>() {
        @Override
        public GithubUsers createFromParcel(Parcel in) {
            return new GithubUsers(in);
        }

        @Override
        public GithubUsers[] newArray(int size) {
            return new GithubUsers[size];
        }
    };

    @SerializedName("login")
    private String userName;

    @SerializedName("avatar_url")
    private String avatarUrl;


    //Constructors
    public GithubUsers(String userName, String avatarUrl){
        this.userName = userName;
        this.avatarUrl = avatarUrl;

    }

    protected GithubUsers(Parcel in) {
        userName = in.readString();
        avatarUrl = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.avatarUrl);

    }
}
