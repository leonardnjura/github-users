package com.example.githubusers;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import com.example.githubusers.model.UserProfile;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class UserProfileParcelTest {

    @Test
    public void testUserProfileParcel () {
        String userName = "nellyk";
        String avatarUrl = "https://avatars3.githubusercontent.com/u/3062772?v=4";
        String profileUrl = "https://api.github.com/users/nellyk";
        String name = "Nelly Kiboi";
        String followers = "50";
        String following = "107";
        String company = null;
        String publicRepos = "44";
        String bio = "Hi currently working as  software engineer.";

        Parcel parcel = Parcel.obtain();

        UserProfile userProfile = new UserProfile(
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

        userProfile.writeToParcel(parcel, userProfile.describeContents());
        parcel.setDataPosition(0);
        UserProfile getProfileFromParcel = UserProfile.CREATOR.createFromParcel(parcel);

        assertThat(getProfileFromParcel.getUserName(), is(userName));
        assertThat(getProfileFromParcel.getAvatarUrl(), is(avatarUrl));
        assertThat(getProfileFromParcel.getFollowers(), is(followers));
        assertThat(getProfileFromParcel.getProfileUrl(), is(profileUrl));
        assertThat(getProfileFromParcel.getName(), is(name));
        assertThat(getProfileFromParcel.getFollowing(), is(following));
        assertThat(getProfileFromParcel.getCompany(), is(company));
        assertThat(getProfileFromParcel.getPublicRepos(), is(publicRepos));
        assertThat(getProfileFromParcel.getBio(), is(bio));
    }
}
