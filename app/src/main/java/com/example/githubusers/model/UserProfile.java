package com.example.githubusers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("PMD")
public class UserProfile implements Parcelable {
    @SerializedName("login")
    private String userName;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("url")
    private String profileUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String following;

    @SerializedName("company")
    private String company;

    @SerializedName("public_repos")
    private String publicRepos;

    @SerializedName("bio")
    private String bio;

    public static final Parcelable.Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    //Constructors
    public UserProfile(Parcel in) {
        this.userName = in.readString();
        this.avatarUrl = in.readString();
        this.profileUrl = in.readString();
        this.name = in.readString();
        this.followers = in.readString();
        this.following = in.readString();
        this.company = in.readString();
        this.publicRepos = in.readString();
        this.bio = in.readString();
    }

    public UserProfile(){} //Pmd Complains

    public UserProfile(String userName, String avatarUrl, String profileUrl, String name, String followers, String following, String company, String publicRepos, String bio) {
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.profileUrl = profileUrl;
        this.name = name;
        this.followers = followers;
        this.following = following;
        this.company = company;
        this.publicRepos = publicRepos;
        this.bio = bio;
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

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getFollowers(){
        return followers;
    }
    public void setFollowers(String followers){
        this.followers = followers;
    }

    public String getFollowing(){
        return following;
    }
    public void setFollowing(String following){
        this.following = following;
    }

    public String getCompany(){
        return company;
    }
    public void setCompany(String company){
        this.company = company;
    }

    public String getPublicRepos(){
        return publicRepos;
    }
    public void setPublicRepos(String publicRepos){
        this.publicRepos = publicRepos;
    }

    public String getBio(){
        return bio;
    }
    public void setBio(String bio){
        this.bio = bio;
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
        dest.writeString(this.name);
        dest.writeString(this.followers);
        dest.writeString(this.following);
        dest.writeString(this.company);
        dest.writeString(this.publicRepos);
        dest.writeString(this.bio);
    }
}
