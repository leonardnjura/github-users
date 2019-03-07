package com.example.githubusers.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.githubusers.R;

public class MainActivity extends AppCompatActivity {

    String[] githubUsers ={
            "@leo",
            "@johngorithm",
            "@codeplus254",
            "@jb43daysGalore",
            "@nateLEHI",
            "@pendo_elizabeth",
            "@trina",
            "@tiffany",
            "@sanchez",
            "@eliza",
            "@monalisa",
            "@dilan"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.lvUsers);

        /**
         * Assign adapter to ListView
         * */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, githubUsers);
        listView.setAdapter(adapter);

        /**
         * Assign listener to ListView
         * */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition = position;
                String userName = (String) listView.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), "You clicked "+userName+" at position "+itemPosition+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("EXTRA_USERNAME", userName);
                startActivity(intent);

            }
        });

    }

    public void launchUserDetails(View view){
        //
    }
}
