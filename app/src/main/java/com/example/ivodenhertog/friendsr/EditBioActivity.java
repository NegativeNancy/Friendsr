package com.example.ivodenhertog.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditBioActivity extends AppCompatActivity {
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bio);

        // Create activity with old bio so the user can change it.
        Intent intent = getIntent();
        String originalBio = (String) intent.getSerializableExtra("bio");
        userName = (String) intent.getSerializableExtra("name");

        TextView profileName = findViewById(R.id.edit_title);
        EditText changeBio = findViewById(R.id.edit_bio_box);

        String title = "Edit bio of " + userName;
        profileName.setText(title);
        changeBio.setText(originalBio);
    }

    public void applyClicked(View view) {
        // When apply is clicked, save new bio in SharedPrefence.
        EditText editText = findViewById(R.id.edit_bio_box);

        String bio = editText.getText().toString();

        SharedPreferences.Editor editor = getSharedPreferences(userName, MODE_PRIVATE).edit();
        editor.putString("bio", bio);
        editor.apply();

        // Give result back to parrent.
        setResult(RESULT_OK);
        finish();
    }
}