package com.example.ml_2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAOChraracter {

    @Insert
    public void addCharacter(Character character);
    @Update
    public void updateCharacter(Character character);
    @Delete
    public void deleteCharacter(Character character);
    @Query("select * from characters")
    public List<Character> getAllCharacters();
    @Query("select * from characters where name==:name")
    public Character getCharacter(String name);
    @Query("DELETE from characters")
    public void deleteAll();

}
