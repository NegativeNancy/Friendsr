package com.example.ivodenhertog.friendsr;

import java.io.Serializable;

class Friend implements Serializable {
    private final String name;
    private final String bio;
    private final int drawableId;
    private float rating;

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public Friend(String name, String bio, int drawableId) {
        this.name = name;
        this.bio = bio;
        this.drawableId = drawableId;
    }
}
