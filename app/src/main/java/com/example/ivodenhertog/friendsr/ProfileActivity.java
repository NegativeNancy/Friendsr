package com.example.ivodenhertog.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    private String userName;
    private Friend retrievedFriend;
    private CharSequence originalBio;

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
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");
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

        String customBio = prefs.getString("bio", null);
        if (customBio != null) {
            profileBio.setText(customBio);
        } else {
            // Calculates which temp bio should be displayed.
            String placeholderBioVersion = "placeholder_bio_" + (userId % 3);
            int bioId = getResources().getIdentifier(placeholderBioVersion, "string", getPackageName());
            profileBio.setText(bioId);
        }
        originalBio = profileBio.getText();
    }


    // Functio to store rating in SharedPreference of profile.
    private class ProfileRatingClicked implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            SharedPreferences.Editor editor = getSharedPreferences(userName, MODE_PRIVATE).edit();
            editor.putFloat("rating", rating);
            editor.apply();
        }
    }

    // Function to create on-screen menu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return(true);
    }

    // Function that handles the events when menu button is pressed.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_bio:
                // Start new activity to change bio.
                Intent intent = new Intent(ProfileActivity.this, EditBioActivity.class);
                intent.putExtra("name", userName);
                intent.putExtra("bio", originalBio);
                startActivityForResult(intent, 1);
                return(true);
            case R.id.about:
                Toast.makeText(this, R.string.about_toast, Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Function to reload activity to show new bio.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                startActivity(getIntent());
                finish();
            }
        }
    }
}
