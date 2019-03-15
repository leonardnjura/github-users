package com.example.githubusers.presenter;

import android.util.Log;

import com.example.githubusers.model.UserProfile;
import com.example.githubusers.service.GithubService;
import com.example.githubusers.view.UserDetailParentView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserProfilePresenter {
    /* package */ static final String TAG = "UserProfilePresenter";
    /* package */ final UserDetailParentView view;
    private GithubService service;

    public UserProfilePresenter(UserDetailParentView view, GithubService service) {
        this.view = view;

        if (this.service == null) {
            this.service = service;
        }
    }

    public void getUserProfile(String userName) {
        this.service
                .getAPI()
                .fetchProfile(userName)
                .enqueue(new Callback<UserProfile>() {
                    @Override
                    public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                        UserProfile userInfo = response.body();

                        if (userInfo != null && userInfo.getUserName() != null) {
                            view.displayUserProfile(userInfo);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserProfile> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }
                });

    }
}
