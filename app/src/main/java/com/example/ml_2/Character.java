package com.example.ml_2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "characters")
public class Character {

    @PrimaryKey
    @ColumnInfo(name = "name")
    @SerializedName("name")
    public String name;

    @ColumnInfo(name = "image")
    @SerializedName("image")
    public String PictureResourse;

    @TypeConverters({ArrayConverter.class})
    @ColumnInfo(name = "episode")
    @SerializedName("episode")
    public String[] description;

    @Ignore
    public Character(){}

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