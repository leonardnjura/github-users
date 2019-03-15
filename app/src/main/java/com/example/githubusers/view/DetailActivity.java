package com.example.githubusers.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.githubusers.R;
import com.example.githubusers.model.UserProfile;
import com.example.githubusers.presenter.UserProfilePresenter;
import com.example.githubusers.service.GithubService;


public class DetailActivity extends AppCompatActivity implements UserDetailParentView {
    private static final String TAG = "DetailActivity";
    public static String USER_INFO_KEY = "userData";
    public UserProfile ghUserInfo;

    private final GithubService service = new GithubService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState != null) {
            this.ghUserInfo = savedInstanceState.getParcelable(USER_INFO_KEY);
            this.displayUserProfile(this.ghUserInfo);
        } else {
            // Caller Intent
            Intent incomingIntent = getIntent();
            if (incomingIntent.hasExtra("EXTRA_USERNAME")) {
                String userName = incomingIntent.getStringExtra("EXTRA_USERNAME");

                UserProfilePresenter profilePresenter = new UserProfilePresenter(this, service);
                profilePresenter.getUserProfile(userName);
                Log.i(TAG, "USERNAME:: " +userName);

                // Set title bar text
                String atSignUsername = this.getResources().getString(R.string.at_sign_username, userName);
                getSupportActionBar().setTitle(atSignUsername);

            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid username, Please try again", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        Log.i(TAG, "ON-CREATE[LIFECYCLE] INVOKED");

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(USER_INFO_KEY, this.ghUserInfo);
        Log.i(TAG, "ON-SAVE-INSTANCE[STATE] INVOKED");
    }

    @Override
    public void displayUserProfile(UserProfile userProfile) {
        ghUserInfo = userProfile;

        ImageView userImageElement = findViewById(R.id.ivUserAvatar);
        TextView userNameElement = findViewById(R.id.tvUserName);
        TextView nameElement = findViewById(R.id.tvName);
        TextView bioElement = findViewById(R.id.tvBio);
        TextView publicReposElement = findViewById(R.id.tvRepos);
        TextView companyElement = findViewById(R.id.tvCompany);
        TextView followingElement = findViewById(R.id.tvFollowing);
        TextView followersElement = findViewById(R.id.tvFollowers);

        Glide.with(this)
                .asBitmap()
                .load(userProfile.getAvatarUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(1)))
                .into(userImageElement);
        // API Required fields
        userNameElement.setText(userProfile.getUserName());
        publicReposElement.setText(userProfile.getPublicRepos());
        followingElement.setText(userProfile.getFollowing());
        followersElement.setText(userProfile.getFollowers());

        // API Optional fields
        if (userProfile.getName() == null) {
            nameElement.setText(getString(R.string.name_placeholder));
        } else {
            nameElement.setText(userProfile.getName());
        }
        if (userProfile.getBio() == null) {
            bioElement.setText(getString(R.string.bio_placeholder));
        } else {
            bioElement.setText(userProfile.getBio());
        }
        if (userProfile.getCompany() == null) {
            companyElement.setText(getString(R.string.company_placeholder));
        } else {
            companyElement.setText(userProfile.getCompany());
        }

    }
}
