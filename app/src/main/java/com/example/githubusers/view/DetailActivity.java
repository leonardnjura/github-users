package com.example.githubusers.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    public String userName;
    public String atSignUsername;
    public ProgressDialog progressDialog;

    private final GithubService service = new GithubService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set custom toolbar as default action bar
        Toolbar toolbar = findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent incomingIntent = getIntent();


        //Get the caller intent
        Intent intent = getIntent();
        userName = intent.getStringExtra("EXTRA_USERNAME");


        // Set title bar text
        atSignUsername = this.getResources().getString(R.string.at_sign_username, userName);
        getSupportActionBar().setTitle(atSignUsername);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();



        if (savedInstanceState != null) {
            this.ghUserInfo = savedInstanceState.getParcelable(USER_INFO_KEY);
            if (this.ghUserInfo != null) {
                this.displayUserProfile(this.ghUserInfo);
            } else {
                fetchData(incomingIntent);
            }
        } else {
            fetchData(incomingIntent);
        }


        Log.i(TAG, "ON-CREATE[LIFECYCLE] INVOKED");

    }


    private void fetchData(Intent newIntent) {
        // HANDLING INCOMING INTENT
        if (newIntent.hasExtra("EXTRA_USERNAME")) {

            UserProfilePresenter profilePresenter = new UserProfilePresenter(this, service);
            profilePresenter.getUserProfile(userName);
            Log.i(TAG, "USERNAME:: " +userName);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Invalid username, Please try again", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(USER_INFO_KEY, this.ghUserInfo);
        Log.i(TAG, "ON-SAVE-INSTANCE[STATE] INVOKED");
    }

    @Override
    public void displayUserProfile(UserProfile userProfile) {
        progressDialog.dismiss();

        this.ghUserInfo = userProfile;
        this.userName = userProfile.getUserName();

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
        userNameElement.setText(atSignUsername);
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

    // TOOLBAR MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate toolbar menu
        getMenuInflater().inflate(R.menu.detail_toolbar_menu, menu);
        return true;
    }

    // Handle all menu items [switch]
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_share:
                Toast.makeText(this, "Share option selected..", Toast.LENGTH_SHORT).show();
                if (this.userName != null) {
                    String text = "Checkout this awesome developer @" + this.userName + "\nhttps://github.com/" + this.userName;
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(intent, "Share via"));
                } else {
                    Toast.makeText(this, "Shareable user data not found", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.home:
                Toast.makeText(this, "Home option selected..", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_refresh:
                Toast.makeText(this, "Refresh option selected..", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void handleError() {
        progressDialog.dismiss();
        Toast.makeText(this, "Something went wrong.. Please try again", Toast.LENGTH_SHORT).show();
    }
}
