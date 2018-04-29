package com.example.ivodenhertog.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Use loadProfile to display the correct profile in the activity.
        Intent intent = getIntent();
        loadProfile(intent);
    }

    private void loadProfile(Intent intent) {
        // Retrieve the profile that has been clicked.
        Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");
        int userId = intent.getIntExtra("position", 0);
        userName = retrievedFriend.getName();
        int userImage = retrievedFriend.getDrawableId();

        // Find necessary view items.
        ImageView profileImage = findViewById(R.id.profileImage);
        TextView profileName = findViewById(R.id.profileName);
        TextView profileBio = findViewById(R.id.profileBio);
        RatingBar profileRating = findViewById(R.id.profileRating);

        // Set correct image, name and rating in views.
        profileImage.setImageDrawable(getResources().getDrawable(userImage));
        profileName.setText(userName);
        profileRating.setOnRatingBarChangeListener(new ProfileRatingClicked());

        // Gather stored rating for profile.
        SharedPreferences prefs = getSharedPreferences(userName, MODE_PRIVATE);

        // Set rating in view.
        Float rating = prefs.getFloat("rating", 0);
        profileRating.setRating(rating);

        // Calculates which temp bio should be displayed.
        String placeholderBioVersion = "placeholder_bio_" + (userId % 3);
        int bioId = getResources().getIdentifier(placeholderBioVersion, "string", getPackageName());

        // Set bio and make view scrollable.
        profileBio.setText(bioId);
        profileBio.setMovementMethod(new ScrollingMovementMethod());
    }

    private class ProfileRatingClicked implements RatingBar.OnRatingBarChangeListener {
        // Store rating in SharedPreference of profile.
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            SharedPreferences.Editor editor = getSharedPreferences(userName, MODE_PRIVATE).edit();

            editor.putFloat("rating", rating);
            editor.apply();
        }
    }

    // Todo: create function that stores new bio in SharedPreferences.
}
