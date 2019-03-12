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

    @SerializedName("url")
    private String profileUrl;


    //Constructors
    public GithubUsers(String userName, String avatarUrl, String profileUrl){
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.profileUrl = profileUrl;

    }

    protected GithubUsers(Parcel in) {
        userName = in.readString();
        avatarUrl = in.readString();
        profileUrl = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.profileUrl);

    }
}
