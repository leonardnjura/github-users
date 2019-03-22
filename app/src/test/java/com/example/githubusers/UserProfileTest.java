package com.example.githubusers;

import com.example.githubusers.model.UserProfile;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserProfileTest {
    private final String userName = "nellyk";
    private final String avatarUrl = "https://avatars3.githubusercontent.com/u/3062772?v=4";
    private final String profileUrl = "https://api.github.com/users/nellyk";
    private final String name = "Nelly Kiboi";
    private final String followers = "50";
    private final String following = "107";
    private final String company = null;
    private final String publicRepos = "44";
    private final String bio = "Hi currently working as  software engineer.";


    private UserProfile userProfile = new UserProfile();

    @Test
    public void testUserProfileInstanceWithData() {
        UserProfile profile = new UserProfile(
                userName,
                avatarUrl,
                profileUrl,
                name,
                followers,
                following,
                company,
                publicRepos,
                bio
        );
        assertEquals(userName, profile.getUserName());

    }

    @Test
    public void testGetCompany() {
        assertEquals(company, userProfile.getCompany());
    }

    @Test
    public void testSetImageUrl() {
        userProfile.setAvatarUrl(avatarUrl);
        assertEquals(avatarUrl, userProfile.getAvatarUrl());
    }

    @Test
    public void testSetUserName() {
        userProfile.setUserName(userName);
        assertEquals(userName, userProfile.getUserName());
    }

    @Test
    public void testSetFollowing() {
        userProfile.setFollowing(following);
        assertEquals(following, userProfile.getFollowing());
    }

    @Test
    public void testSetFollowers() {
        userProfile.setFollowers(followers);
        assertEquals(followers, userProfile.getFollowers());
    }

    @Test
    public void testSetCompany() {
        userProfile.setCompany(company);
        assertEquals(company, userProfile.getCompany());
    }

    @Test
    public void testSetBio() {
        userProfile.setBio(bio);
        assertEquals(bio, userProfile.getBio());
    }

    @Test
    public void testSetPublicRepos() {
        userProfile.setPublicRepos(publicRepos);
        assertEquals(publicRepos, userProfile.getPublicRepos());
    }

    @Test
    public void testDescContent () {
        int val = userProfile.describeContents();
        assertEquals(val, 0);
    }

    @Test
    public void testSetProfileUrl() {
        userProfile.setProfileUrl(profileUrl);
        assertEquals(profileUrl, userProfile.getProfileUrl());
    }

    @Test
    public void testSetName() {
        userProfile.setName(name);
        assertEquals(name, userProfile.getName());
    }


}
