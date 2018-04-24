package com.example.ivodenhertog.friendsr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Friend> friends = new ArrayList<>();
    private Friend friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 10; i++) {
            friends.add(addNewFriend(i));
        }
    }

    public Friend addNewFriend(int i) {
        String[] names = getResources().getStringArray(R.array.names);

        String name = names[i];
        String bio = "This is the bio of " + name;
        String nameLower = name.toLowerCase();

        int id = getResources().getIdentifier(
                "" +  nameLower, "drawable", getPackageName());

        return new Friend(name, bio, id);
    }
}
