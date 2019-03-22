package com.example.githubusers;

import com.example.githubusers.model.GithubUsers;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class GithubUsersTest {
    private String userName = "Baboonski_Moskva";
    private String avatarUrl = "https://cdn.pixabay.com/photo/2014/07/18/09/03/mandrill-396289__340.jpg";
    private GithubUsers githubUserModel = new GithubUsers(userName, avatarUrl);

    @Test
    public void testGetUsername() {
        assertEquals(userName, githubUserModel.getUserName());
    }

    @Test
    public void testGetAvatarUrl() {
        assertEquals(avatarUrl, githubUserModel.getAvatarUrl());
    }

    @Test
    public void testSetUserName() {
        final String newUserName = "Manooski_Moskva";
        githubUserModel.setUserName(newUserName);
        assertEquals(githubUserModel.getUserName(), newUserName);
    }
    @Test
    public void testSetImageUrl() {
        final String avatarUrl = "https://cdn.pixabay.com/photo/2019/02/15/07/38/mother-3997967__340.jpg";
        githubUserModel.setAvatarUrl(avatarUrl);
        assertEquals(githubUserModel.getAvatarUrl(), avatarUrl);
    }
}
