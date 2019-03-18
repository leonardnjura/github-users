package com.example.githubusers.view;

import com.example.githubusers.model.UserProfile;

public interface UserDetailParentView {
    void displayUserProfile(UserProfile userProfile);
    void handleError();
}
