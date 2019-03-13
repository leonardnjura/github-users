package com.example.githubusers.view;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.githubusers.R;
import com.example.githubusers.adapter.GithubAdapter;
import com.example.githubusers.model.GithubUsers;
import com.example.githubusers.presenter.GithubUsersPresenter;
import com.example.githubusers.service.GithubService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersParentView {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    public static final String USERS_KEY = "users";
    public static final String RECYCLER_VIEW_KEY = "viewKey";
    public List<GithubUsers> ghUsers;
    RecyclerView.LayoutManager mLayoutManager;
    Parcelable recyclerViewParcelable;

    private final GithubService service = new GithubService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rvUsers);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        if (savedInstanceState != null) {
            this.ghUsers = savedInstanceState.getParcelableArrayList(USERS_KEY);
            this.displayUsers(ghUsers);
        } else  {
            // PRESENTER
            GithubUsersPresenter usersPresenter = new GithubUsersPresenter(this, service);
            usersPresenter.fetchUsers();
        }

        Log.i(TAG, "ON-CREATE[LIFECYCLE] INVOKED");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (recyclerViewParcelable != null) {
            mLayoutManager.onRestoreInstanceState(recyclerViewParcelable);
        }
        Log.i(TAG, "ON-RESUME[LIFECYCLE] INVOKED");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(USERS_KEY, (ArrayList<GithubUsers>) this.ghUsers);
        this.recyclerViewParcelable = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(RECYCLER_VIEW_KEY, recyclerViewParcelable);

        Log.i(TAG, "ON-SAVE-INSTANCE[STATE] INVOKED");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            recyclerViewParcelable = savedInstanceState.getParcelable(RECYCLER_VIEW_KEY);
        }
        Log.i(TAG, "ON-RESTORE-INSTANCE[STATE] INVOKED");
    }

    @Override
    public void displayUsers(List<GithubUsers> githubUsersList) {
        ghUsers = githubUsersList;
        GithubAdapter adapter = new GithubAdapter(githubUsersList, this);
        // Set layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Attach adapter
        mRecyclerView.setAdapter(adapter);
    }

}
