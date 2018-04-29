package com.example.ivodenhertog.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create all the profiles to be displayed.
        String[] names = getResources().getStringArray(R.array.names);
        for (int i = 0; i < names.length; i++) {
            friends.add(addNewFriend(i));
        }

        // Use adapter to fill the view with profiles.
        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new GridItemClickListener());
    }

    private Friend addNewFriend(int i) {
        // Create profiles to be displayed in the grid view.
        String[] names = getResources().getStringArray(R.array.names);

        String name = names[i];
        String bio = "This is the bio of " + name;
        String nameLower = name.toLowerCase();

        // Get drawable to display as profile picture.
        int id = getResources().getIdentifier(
                "" +  nameLower, "drawable", getPackageName());

        return new Friend(name, bio, id);
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        // Create an intent to send when an profile is clicked in the GridView.
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }

    // Todo: create function that shows menu with multiple options.
}
