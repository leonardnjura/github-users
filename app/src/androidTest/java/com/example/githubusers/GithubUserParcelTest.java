package com.example.githubusers;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import com.example.githubusers.model.GithubUsers;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class GithubUserParcelTest {

    @Test
    public void testParcelable() {
        String userName = "nellyk";
        String avatarUrl = "https://avatars3.githubusercontent.com/u/3062772?v=4";

        Parcel parcel = Parcel.obtain();
        GithubUsers user = new GithubUsers(userName, avatarUrl);

        user.writeToParcel(parcel, user.describeContents());
        parcel.setDataPosition(0);
        GithubUsers getFromParcel = GithubUsers.CREATOR.createFromParcel(parcel);

        assertThat(getFromParcel.getUserName(), is(userName));
        assertThat(getFromParcel.getAvatarUrl(), is(avatarUrl));
    }
}
