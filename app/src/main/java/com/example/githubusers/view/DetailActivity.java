package com.example.githubusers.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.githubusers.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Get the caller intent
        Intent intent = getIntent();
        String extra_username = intent.getStringExtra("EXTRA_USERNAME");

        //Set title bar text
        getSupportActionBar().setTitle(extra_username);

        TextView tvUserName = findViewById(R.id.tvUserName);
        TextView tvFirstName = findViewById(R.id.tvFirstName);
        TextView tvLastName = findViewById(R.id.tvLastName);
        TextView tvRepos = findViewById(R.id.tvRepos);
        TextView tvBio = findViewById(R.id.tvBio);

        tvUserName.setText(extra_username);
        tvFirstName.setText("");
        tvLastName.setText("");
        tvRepos.setText("");
        tvBio.setText("");

    }
}
