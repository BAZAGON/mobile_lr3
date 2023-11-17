package com.example.ml_2;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;


public class DBRepository {
    private DAOChraracter daoChraracter;
    private List<Character> allChars;

    DBRepository(Application application){
        DataBase db = DataBase.getDataBase(application);
        daoChraracter = db.getCharacterDAO();
    }

    void Insert(Character chert) {
        new InsertCharacterAsyncTask(daoChraracter).execute(chert);
    }

    void ClearAll() {
        new ClearAllCharactersAsyncTask(daoChraracter).execute();
    }

    private static class InsertCharacterAsyncTask extends AsyncTask<Character, Void, Void> {
        private DAOChraracter daoChraracter;


        private InsertCharacterAsyncTask(DAOChraracter daoChraracter) {
            this.daoChraracter = daoChraracter;
        }

        @Override
        protected Void doInBackground(Character... chars) {
            daoChraracter.addCharacter(chars[0]);

            return null;
        }
    }

    private static class ClearAllCharactersAsyncTask extends AsyncTask<Void, Void, Void> {
        private DAOChraracter daoChraracter;

        private ClearAllCharactersAsyncTask(DAOChraracter daoChraracter) {
            this.daoChraracter = daoChraracter;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            daoChraracter.deleteAll();

            return null;
        }
    }

}
