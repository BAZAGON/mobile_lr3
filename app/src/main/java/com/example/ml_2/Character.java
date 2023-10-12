package com.example.ml_2;

import android.graphics.Picture;

public class Character {
    private String name;
    private int PictureResourse;

    private String description;

    public Character(String name, int picture, String description) {
        this.name = name;
        this.PictureResourse = picture;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPictureResourse() {
        return this.PictureResourse;
    }

    public void setPictureResourse(int PictureResource) {
        this.PictureResourse = PictureResource;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {this.description = description; }
}