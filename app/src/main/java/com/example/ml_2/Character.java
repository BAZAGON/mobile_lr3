package com.example.ml_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

@Entity
public class Character {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Nullable
    @ColumnInfo(name = "name")
    @SerializedName("name")
    public String name;

    @Nullable
    @ColumnInfo(name = "image")
    @SerializedName("image")
    public String PictureResourse;

    @Nullable
    @TypeConverters({ArrayConverter.class})
    @ColumnInfo(name = "episode")
    @SerializedName("episode")
    public String[] description;

    public Character(){}

    public Character(int id, String name, String picture, String[] description) {
        this.id = id;
        this.name = name;
        this.PictureResourse = picture;
        this.description = description;
    }

    public int getId(){return this.id;}
    public void setId(int id){this.id = id;}
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