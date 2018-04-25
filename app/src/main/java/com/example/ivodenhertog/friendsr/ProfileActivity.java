package com.example.ivodenhertog.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        String userName = retrievedFriend.getName();
        int userImage = retrievedFriend.getDrawableId();

        ImageView image = findViewById(R.id.profileImage);
        TextView name = findViewById(R.id.profileName);

        image.setImageDrawable(getResources().getDrawable(userImage));
        name.setText(userName);
    }
}
