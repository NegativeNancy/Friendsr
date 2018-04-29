package com.example.ivodenhertog.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private ArrayList<Friend> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();

        friends = (ArrayList<Friend>) intent.getSerializableExtra("friends");
    }

    private void resetBio() {
        for (int i = 0, j = friends.size(); i < j; i++) {
            Friend friend = friends.get(i);
            String userName = friend.getName();
            // Gather stored rating for profile.
            SharedPreferences prefs = getSharedPreferences(userName, MODE_PRIVATE);

            // Set rating in view.
            String customBio = prefs.getString("bio", null);

            if (customBio != null) {
                SharedPreferences.Editor editor = getSharedPreferences(userName, MODE_PRIVATE).edit();
                customBio = null;
                editor.putString("bio", customBio);
                editor.apply();
            }
        }
    }

    private void resetRating() {
        for (int i = 0, j = friends.size(); i < j; i++) {
            Friend friend = friends.get(i);
            String userName = friend.getName();
            // Gather stored rating for profile.
            SharedPreferences prefs = getSharedPreferences(userName, MODE_PRIVATE);

            // Set rating in view.
            float rating = prefs.getFloat("rating", 0);

            // If there is a rating reset it.
            if (rating > 0) {
                SharedPreferences.Editor editor = getSharedPreferences(userName, MODE_PRIVATE).edit();
                rating = 0;
                editor.putFloat("rating", rating);
                editor.apply();
            }
        }
    }

    public void resetClicked(View view) {
        CheckBox ratingReset = findViewById(R.id.btn_reset_rating);
        CheckBox bioReset = findViewById(R.id.btn_reset_bio);
        CheckBox resetAll = findViewById(R.id.btn_reset_all);

        // Check al checkboxes at once.
        if (resetAll.isChecked()) {
            ratingReset.setChecked(true);
            bioReset.setChecked(true);
        } else {
            ratingReset.setChecked(false);
            bioReset.setChecked(false);
        }
    }

    // Function to reset requested values.
    public void applyClicked(View view) {
        CheckBox ratingReset = findViewById(R.id.btn_reset_rating);
        CheckBox bioReset = findViewById(R.id.btn_reset_bio);

        if (ratingReset.isChecked()) {
            resetRating();
        }

        if (bioReset.isChecked()) {
            resetBio();
        }

        finish();
    }
}
