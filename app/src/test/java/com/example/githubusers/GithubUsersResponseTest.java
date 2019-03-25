package com.example.githubusers;

import android.util.Log;

import com.example.githubusers.model.GithubUsers;
import com.example.githubusers.model.GithubUsersResponse;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

public class GithubUsersResponseTest {
    private GithubUsersResponse githubUsersResponse = new GithubUsersResponse();
    private List<GithubUsers> usersList = new ArrayList<>();
    private GithubUsers oneUser = new GithubUsers("james_blunt", "http://images.com/james_blunt.png");
    private GithubUsers twoUser = new GithubUsers("luther_vandross", "http://images.com/luther_vandross.png");

    private static final String TAG = "GithubUsersResponseTest";


    @Test
    public void testGithubUsersResponse() {

        usersList.add(oneUser);
        usersList.add(twoUser);

        githubUsersResponse.setUsers(usersList);
        assertEquals(usersList, githubUsersResponse.getUsers());
    }
}
