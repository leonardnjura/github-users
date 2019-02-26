package com.example.githubusers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    MyCollections myCollections = new MyCollections();


    private static final int VERTICAL_ITEM_SPACE = 43;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data to populate the RecyclerView
        ArrayList<String> itemNames = new ArrayList<>();
        for(int i=0; i<myCollections.githubUsers.length; i++){
            itemNames.add(myCollections.githubUsers[i]);
        }

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Add divider decoration: Space
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));

        //Add divider decoration: Drawable
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, R.drawable.divider));

        adapter = new MyRecyclerViewAdapter(this, itemNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}