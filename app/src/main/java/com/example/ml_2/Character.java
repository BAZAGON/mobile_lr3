package com.example.ml_2;

import com.google.gson.annotations.SerializedName;

public class Character {
    @SerializedName("name")
    public String name;
    @SerializedName("image")
    public String PictureResourse;
    @SerializedName("episode")
    public String[] description;

    public Character(String name, String picture, String[] description) {
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

    public String getPictureResourse() {
        return this.PictureResourse;
    }

    public void setPictureResourse(String PictureResource) {
        this.PictureResourse = PictureResource;
    }

    public String[] getDescription() {
        return this.description;
    }

    public void setDescription(String[] description) {this.description = description; }
}