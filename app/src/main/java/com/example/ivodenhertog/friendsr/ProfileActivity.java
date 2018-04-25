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
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        loadProfile(intent);
    }

    public void loadProfile(Intent intent) {
        Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");
        int userId = intent.getIntExtra("position", 0);

        userName = retrievedFriend.getName();
        int userImage = retrievedFriend.getDrawableId();

        ImageView profileImage = findViewById(R.id.profileImage);
        TextView profileName = findViewById(R.id.profileName);
        TextView profileBio = findViewById(R.id.profileBio);
        RatingBar profileRating = findViewById(R.id.profileRating);

        profileImage.setImageDrawable(getResources().getDrawable(userImage));
        profileName.setText(userName);
        profileRating.setOnRatingBarChangeListener(new ProfileRatingClicked());

        SharedPreferences prefs = getSharedPreferences(userName, MODE_PRIVATE);

        Float rating = prefs.getFloat("rating", 0);
        profileRating.setRating(rating);

        String placeholderBioVersion = "placeholder_bio_" + (userId % 3);
        int resId = getResources().getIdentifier(placeholderBioVersion, "string", getPackageName());

        profileBio.setText(resId);
        profileBio.setMovementMethod(new ScrollingMovementMethod());
    }

    private class ProfileRatingClicked implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            SharedPreferences.Editor editor = getSharedPreferences(userName, MODE_PRIVATE).edit();

            editor.putFloat("rating", rating);
            editor.apply();
        }
    }
}
