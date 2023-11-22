package com.example.ml_2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.ml_2.DAOChraracter;

@Database(entities = {Character.class},version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract DAOChraracter  daoChraracter();

    private static volatile DataBase INSTANCE;

    static DataBase getDataBase(Context context){
        if(INSTANCE == null){
            synchronized (DataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DataBase.class, "CharacterDB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
