package com.example.ivodenhertog.friendsr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class FriendsAdapter extends ArrayAdapter<Friend> {

    private final ArrayList friends;

    public FriendsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Friend> objects) {
        super(context, resource, objects);
        friends = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.grid_item, parent, false);
        }

        // Create views to display in grid view.
        ImageView profileImage = convertView.findViewById(R.id.profileImage);
        TextView profileName = convertView.findViewById(R.id.profileName);

        Friend inputFriend = (Friend) friends.get(position);

        int inputId = inputFriend.getDrawableId();

        profileImage.setImageDrawable(getContext().getResources().getDrawable(inputId));
        profileName.setText(inputFriend.getName());

        return convertView;
    }
}
