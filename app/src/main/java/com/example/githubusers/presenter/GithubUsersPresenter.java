package com.example.githubusers.presenter;

import android.util.Log;

import com.example.githubusers.model.GithubUsers;
import com.example.githubusers.model.GithubUsersResponse;
import com.example.githubusers.service.GithubService;
import com.example.githubusers.view.UsersParentView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GithubUsersPresenter {
    /* package */ static final String TAG = "GithubUsersPresenter";
    /* package */ final UsersParentView view;
    private GithubService service;

    public GithubUsersPresenter(UsersParentView view, GithubService service) {
        this.view = view;

        if (this.service == null) {
            this.service = service;
        }
    }

    public void fetchUsers() {
        this.service
                .getAPI()
                .getUsers()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                        GithubUsersResponse data = response.body();

                        if (data != null && data.getUsers() != null) {
                            List<GithubUsers> githubUsers = data.getUsers();
                            view.getGithubUsers(githubUsers);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }
                });

    }
}
