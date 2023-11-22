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
    void addCharacter(Character character);
    @Update
    public void updateCharacter(Character character);
    @Delete
    public void deleteCharacter(Character character);

    @Query("select  * from Character")
    List<Character> getAll();

    @Query("select * from Character where id==:id")
    public Character getCharacter(int id);
    @Query("DELETE from Character")
    public void deleteAll();

}
