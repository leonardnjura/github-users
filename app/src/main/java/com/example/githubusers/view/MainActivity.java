package com.example.githubusers.view;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

        // Set custom toolbar as default action bar
        Toolbar toolbar = findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);


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

    // TOOLBAR MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate toolbar menu
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);

        // Handle search menu item expand/collapse
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(MainActivity.this, "Search item action view expanded", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(MainActivity.this, "Search item action view collapsed", Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setOnActionExpandListener(onActionExpandListener);


        return true;
    }

    // Handle all menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_search:
                Toast.makeText(this, "Search option selected..", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings option selected..", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
